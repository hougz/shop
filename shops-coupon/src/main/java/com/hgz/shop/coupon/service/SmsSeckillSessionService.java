package com.hgz.shop.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.coupon.entity.SmsSeckillSessionEntity;

import java.util.List;
import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:02:46
 */
public interface SmsSeckillSessionService extends IService<SmsSeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);


    List<SmsSeckillSessionEntity> getLates3DaySession();
}

