package com.hgz.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.order.entity.OmsOrderEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * 订单
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 09:46:56
 */
public interface OmsOrderService extends IService<OmsOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

