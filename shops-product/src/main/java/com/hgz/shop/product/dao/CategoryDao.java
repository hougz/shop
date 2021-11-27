package com.hgz.shop.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgz.shop.product.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
