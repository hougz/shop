package com.hgz.shop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.member.entity.UmsMemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:10:12
 */
public interface UmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

