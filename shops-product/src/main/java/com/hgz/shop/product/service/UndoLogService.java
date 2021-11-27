package com.hgz.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.product.entity.UndoLogEntity;
import com.hgz.shop.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:25
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

