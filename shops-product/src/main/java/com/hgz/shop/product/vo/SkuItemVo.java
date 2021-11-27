package com.hgz.shop.product.vo;


import com.hgz.shop.product.entity.PmsSkuImagesEntity;
import com.hgz.shop.product.entity.PmsSkuInfoEntity;
import com.hgz.shop.product.entity.PmsSpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 * <p>Title: SkuItemVo</p>
 * Description：
 * date：2020/6/24 13:33
 */
@Data
public class SkuItemVo {

	/**
	 * 基本信息
	 */
	PmsSkuInfoEntity info;

	boolean hasStock = true;

	/**
	 * 图片信息
	 */
	List<PmsSkuImagesEntity> images;

	/**
	 * 销售属性组合
	 */
	List<ItemSaleAttrVo> saleAttr;

	/**
	 * 介绍
	 */
	PmsSpuInfoDescEntity desc;

	/**
	 * 参数规格信息
	 */
	List<SpuItemAttrGroup> groupAttrs;

	/**
	 * 秒杀信息
	 */
	SeckillInfoVo seckillInfoVo;
}
