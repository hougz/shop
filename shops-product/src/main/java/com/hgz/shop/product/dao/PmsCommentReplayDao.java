package com.hgz.shop.product.dao;

import com.hgz.shop.product.entity.PmsCommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:26
 */
@Mapper
public interface PmsCommentReplayDao extends BaseMapper<PmsCommentReplayEntity> {
	
}
