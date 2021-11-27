package com.hgz.shop.product.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgz.shop.product.entity.AttrGroupEntity;
import com.hgz.shop.product.vo.SpuItemAttrGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 * 
 * @author firenay
 * @email 1046762075@qq.com
 * @date 2020-05-31 17:06:04
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

	List<SpuItemAttrGroup> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
