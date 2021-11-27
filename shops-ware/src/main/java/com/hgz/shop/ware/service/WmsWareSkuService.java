package com.hgz.shop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.ware.entity.WmsWareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 18:11:13
 */
public interface WmsWareSkuService extends IService<WmsWareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

