package com.hgz.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.order.entity.OmsRefundInfoEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * 退款信息
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 09:46:56
 */
public interface OmsRefundInfoService extends IService<OmsRefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

