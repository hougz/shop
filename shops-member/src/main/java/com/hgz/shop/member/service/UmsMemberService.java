package com.hgz.shop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.member.entity.UmsMemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:10:12
 */
public interface UmsMemberService extends IService<UmsMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

