package com.hgz.shop.order.dao;

import com.hgz.shop.order.entity.OmsOrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 09:46:56
 */
@Mapper
public interface OmsOrderItemDao extends BaseMapper<OmsOrderItemEntity> {
	
}
