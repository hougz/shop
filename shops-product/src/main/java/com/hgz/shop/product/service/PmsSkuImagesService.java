package com.hgz.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.product.entity.PmsSkuImagesEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:26
 */
public interface PmsSkuImagesService extends IService<PmsSkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PmsSkuImagesEntity> getImageBySkuId(Long skuId);
}

