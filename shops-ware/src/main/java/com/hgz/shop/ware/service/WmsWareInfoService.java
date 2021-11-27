package com.hgz.shop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.ware.entity.WmsWareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 18:11:14
 */
public interface WmsWareInfoService extends IService<WmsWareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

