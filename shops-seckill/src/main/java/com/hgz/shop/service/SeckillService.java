package com.hgz.shop.service;

import com.hgz.shop.to.SeckillSkuRedisTo;

import java.util.List;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/27 20:26
 */
public interface SeckillService {

    void uploadSeckillSkuLatest3Day();


    /**
     * 获取当前可以参与秒杀的商品信息
     */
    List<SeckillSkuRedisTo> getSeckill();


    SeckillSkuRedisTo getSkuSeckillInfo(Long skuId);

    /**
     * 执行秒杀
     *
     * @param killId
     * @param key
     * @param num
     * @return
     */
    String kill(String killId, String key, Integer num);


}
