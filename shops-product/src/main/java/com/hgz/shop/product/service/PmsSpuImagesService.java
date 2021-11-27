package com.hgz.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.product.entity.PmsSpuImagesEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * spu图片
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:26
 */
public interface PmsSpuImagesService extends IService<PmsSpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

