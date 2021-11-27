package com.hgz.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.hgz.shop.feign.CouponFeignService;
import com.hgz.shop.feign.ProductFeignService;
import com.hgz.shop.service.SeckillService;
import com.hgz.shop.to.SeckillSkuRedisTo;
import com.hgz.shop.utils.R;
import com.hgz.shop.vo.SeckillSessionsWithSkus;
import com.hgz.shop.vo.SkuInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/27 20:27
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    private final String SESSION_CACHE_PREFIX = "seckill:sessions:";

    private final String SKUKILL_CACHE_PREFIX = "seckill:skus:";

    private final String SKUSTOCK_SEMAPHONE = "seckill:stock:"; // +商品随机码

    /*@Autowired
    private RabbitTemplate rabbitTemplate;*/

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public void uploadSeckillSkuLatest3Day() {
        // 1.扫描最近三天要参加秒杀的商品
        R r = couponFeignService.getLate3DaySession();
        if (r.getCode() == 0) {
            List<SeckillSessionsWithSkus> sessions = r.getData(new TypeReference<List<SeckillSessionsWithSkus>>() {
            });
            // 2.缓存活动信息
            saveSessionInfo(sessions);
            // 3.缓存活动的关联的商品信息
            saveSessionSkuInfo(sessions);
        }
    }

    /**
     * 缓存活动信息
     *
     * @param sessions
     */
    private void saveSessionInfo(List<SeckillSessionsWithSkus> sessions) {
        if (sessions != null) {
            sessions.stream().forEach(session -> {
                long startTime = session.getStartTime().getTime();

                long endTime = session.getEndTime().getTime();
                String key = SESSION_CACHE_PREFIX + startTime + "_" + endTime;
                Boolean hasKey = stringRedisTemplate.hasKey(key);
                if (!hasKey) {
                    // 获取所有商品id
                    List<String> collect = session.getRelationSkus().stream().map(item -> item.getPromotionSessionId() + "-" + item.getSkuId()).collect(Collectors.toList());
                    // 缓存活动信息
                    stringRedisTemplate.opsForList().leftPushAll(key, collect);
                }
            });
        }
    }

    /**
     * 缓存活动的关联的商品信息
     *
     * @param sessions
     */
    private void saveSessionSkuInfo(List<SeckillSessionsWithSkus> sessions) {
        if (sessions != null) {
            sessions.stream().forEach(session -> {
                BoundHashOperations<String, Object, Object> ops = stringRedisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
                session.getRelationSkus().stream().forEach(seckillSkuVo -> {
                    // 1.商品的随机码
                    String randomCode = UUID.randomUUID().toString().replace("-", "");
                    if (!ops.hasKey(seckillSkuVo.getPromotionSessionId() + "-" + seckillSkuVo.getSkuId())) {
                        // 2.缓存商品
                        SeckillSkuRedisTo redisTo = new SeckillSkuRedisTo();
                        BeanUtils.copyProperties(seckillSkuVo, redisTo);
                        // 3.sku的基本数据 sku的秒杀信息
                        R info = productFeignService.skuInfo(seckillSkuVo.getSkuId());
                        if (info.getCode() == 0) {
                            SkuInfoVo skuInfo = info.getData("pmsSkuInfo", new TypeReference<SkuInfoVo>() {
                            });
                            redisTo.setSkuInfoVo(skuInfo);
                        }
                        // 4.设置当前商品的秒杀信息
                        redisTo.setStartTime(session.getStartTime().getTime());
                        redisTo.setEndTime(session.getEndTime().getTime());

                        redisTo.setRandomCode(randomCode);

                        ops.put(seckillSkuVo.getPromotionSessionId() + "-" + seckillSkuVo.getSkuId(), JSON.toJSONString(redisTo));
                        // 如果当前这个场次的商品库存已经上架就不需要上架了
                        // 5.使用库存作为分布式信号量  限流
                        RSemaphore semaphore = redissonClient.getSemaphore(SKUSTOCK_SEMAPHONE + randomCode);
                        semaphore.trySetPermits(seckillSkuVo.getSeckillCount().intValue());
                    }
                });
            });
        }
    }

    /**
     * 获取当前可以参与秒杀的商品信息
     */
    @Override
    public List<SeckillSkuRedisTo> getSeckill() {
        long time = new Date().getTime();
        Set<String> keys = stringRedisTemplate.keys(SESSION_CACHE_PREFIX + "*");
        for (String key : keys) {
            String replace = key.replace(SESSION_CACHE_PREFIX, "");
            String[] split = replace.split("_");
            long statr = Long.parseLong(split[0]);
            long end = Long.parseLong(split[1]);
            if (statr <= time && time <= end) {
                // 2.获取这个秒杀场次的所有商品信息
                List<String> range = stringRedisTemplate.opsForList().range(key, 0, 100);
                BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
                //获取商品信息
                List<String> list = hashOps.multiGet(range);
                if (list != null) {
                    return list.stream().map(item -> {
                        SeckillSkuRedisTo redisTo = JSON.parseObject(item, SeckillSkuRedisTo.class);
                        return redisTo;
                    }).collect(Collectors.toList());
                }
                break;
            }
        }
        return null;
    }

    //根据skuid 获取商品信息
    @Override
    public SeckillSkuRedisTo getSkuSeckillInfo(Long skuId) {
        BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        Set<String> keys = hashOps.keys();
        if (keys != null && keys.size() > 0) {
            String regx = "\\d-" + skuId;
            for (String key : keys) {
                String s = hashOps.get(key);
                SeckillSkuRedisTo skuRedisTo = JSON.parseObject(s, SeckillSkuRedisTo.class);
                // 处理一下随机码
                long time = new Date().getTime();
                if (time <= skuRedisTo.getStartTime() || time >= skuRedisTo.getEndTime()) {
                    skuRedisTo.setRandomCode(null);
                }
                return skuRedisTo;
            }
        }
        return null;
    }

    /**
     * 秒杀操作
     *
     * @param killId
     * @param key
     * @param num
     * @return
     */
    @Override
    public String kill(String killId, String key, Integer num) {

        // 1.获取当前秒杀商品的详细信息
        BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        String json = hashOps.get(killId);
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            SeckillSkuRedisTo redisTo = JSON.parseObject(json, SeckillSkuRedisTo.class);
            // 校验合法性
            long time = new Date().getTime();
            if (time >= redisTo.getStartTime() && time <= redisTo.getEndTime()) {
                // 1.校验随机码跟商品id是否匹配
                String randomCode = redisTo.getRandomCode();
                String skuId = redisTo.getPromotionSessionId() + "-" + redisTo.getSkuId();
                if (randomCode.equals(key) && killId.equals(skuId)) {
                    // 2.说明数据合法
                    BigDecimal limit = redisTo.getSeckillLimit();
                    if (num <= limit.intValue()) {
                        // 3.验证这个人是否已经购买过了
                        String redisKey = "id" + "-" + skuId;//用户id
                        // 让数据自动过期
                        long ttl = redisTo.getEndTime() - redisTo.getStartTime();
                        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(redisKey, num.toString(), ttl < 0 ? 0 : ttl, TimeUnit.MILLISECONDS);
                        if (aBoolean) {
                            //从没买过
                            RSemaphore semaphore = redissonClient.getSemaphore(SKUSTOCK_SEMAPHONE + randomCode);//获取信号量
                            boolean acquire = semaphore.tryAcquire(num);//扣减库存
                            if (acquire) {
                                // 秒杀成功
                                // 快速下单 发送MQ
                                String orderSn = IdWorker.getTimeId() + UUID.randomUUID().toString().replace("-", "").substring(7, 8);
                                SeckillSkuRedisTo orderTo = new SeckillSkuRedisTo();
                                orderTo.setPromotionId(orderSn);
                                orderTo.setSeckillLimit(new BigDecimal(num));
                                orderTo.setSkuId(redisTo.getSkuId());
                                orderTo.setSeckillPrice(redisTo.getSeckillPrice());
                                orderTo.setPromotionSessionId(redisTo.getPromotionSessionId());
                                //rabbitTemplate.convertAndSend("order-event-exchange", "order.seckill.order", orderTo);
                                return orderSn;
                            } else {
                                return null;
                            }
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }

}
