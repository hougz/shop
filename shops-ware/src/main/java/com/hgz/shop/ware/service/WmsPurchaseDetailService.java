package com.hgz.shop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.ware.entity.WmsPurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 18:11:14
 */
public interface WmsPurchaseDetailService extends IService<WmsPurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

