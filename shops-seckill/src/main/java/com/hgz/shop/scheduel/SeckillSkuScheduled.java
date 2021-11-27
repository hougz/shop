package com.hgz.shop.scheduel;

import com.hgz.shop.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/27 20:14  秒杀商品定时上架		[秒杀的定时任务调度]
 */
@Service
@Slf4j
public class SeckillSkuScheduled {

    @Autowired
    private RedissonClient redissonClient;

    private final String upload_lock = "seckill:upload:lock";

    @Autowired
    private SeckillService seckillService;


    /**
     *
     * 8小时执行一次：0 0 0-8 * * ?
     */
    @Scheduled(cron = "0 * * * * ?")
    public void uploadSeckillSkuLatest3Day(){
        log.info("上架秒杀商品的信息");
        // 1.重复上架无需处理 加上分布式锁 状态已经更新 释放锁以后其他人才获取到最新状态
        RLock lock = redissonClient.getLock(upload_lock);
        lock.lock(10, TimeUnit.SECONDS);
        try {
            seckillService.uploadSeckillSkuLatest3Day();
        } finally {
            lock.unlock();
        }
    }

}
