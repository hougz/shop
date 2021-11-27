package com.hgz.shop.order.service.impl;

import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hgz.shop.order.dao.OmsOrderDao;
import com.hgz.shop.order.entity.OmsOrderEntity;
import com.hgz.shop.order.service.OmsOrderService;


@Service("omsOrderService")
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderDao, OmsOrderEntity> implements OmsOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OmsOrderEntity> page = this.page(
                new Query<OmsOrderEntity>().getPage(params),
                new QueryWrapper<OmsOrderEntity>()
        );

        return new PageUtils(page);
    }

}