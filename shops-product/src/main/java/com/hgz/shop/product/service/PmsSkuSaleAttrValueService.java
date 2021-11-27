package com.hgz.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.product.entity.PmsSkuSaleAttrValueEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:26
 */
public interface PmsSkuSaleAttrValueService extends IService<PmsSkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

