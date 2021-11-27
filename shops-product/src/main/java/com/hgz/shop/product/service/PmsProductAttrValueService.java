package com.hgz.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.product.entity.PmsProductAttrValueEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * spu属性值
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:26
 */
public interface PmsProductAttrValueService extends IService<PmsProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

