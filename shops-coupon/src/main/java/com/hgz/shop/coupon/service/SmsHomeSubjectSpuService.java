package com.hgz.shop.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.coupon.entity.SmsHomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:02:46
 */
public interface SmsHomeSubjectSpuService extends IService<SmsHomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

