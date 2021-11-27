package com.hgz.shop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.member.entity.UmsMemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:10:12
 */
public interface UmsMemberCollectSpuService extends IService<UmsMemberCollectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

