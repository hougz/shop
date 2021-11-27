package com.hgz.shop.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.coupon.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:02:46
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

