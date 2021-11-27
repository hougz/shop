package com.hgz.shop.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.coupon.entity.SmsMemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:02:46
 */
public interface SmsMemberPriceService extends IService<SmsMemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

