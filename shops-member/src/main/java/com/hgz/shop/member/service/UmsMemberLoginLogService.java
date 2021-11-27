package com.hgz.shop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.member.entity.UmsMemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:10:12
 */
public interface UmsMemberLoginLogService extends IService<UmsMemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

