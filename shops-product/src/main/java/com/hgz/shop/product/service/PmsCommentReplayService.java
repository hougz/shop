package com.hgz.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.product.entity.PmsCommentReplayEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:26
 */
public interface PmsCommentReplayService extends IService<PmsCommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

