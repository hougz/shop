package com.hgz.shop.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.coupon.entity.SmsSeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:02:46
 */
public interface SmsSeckillSkuRelationService extends IService<SmsSeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

