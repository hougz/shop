package com.hgz.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.order.entity.OmsOrderReturnApplyEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 09:46:56
 */
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

