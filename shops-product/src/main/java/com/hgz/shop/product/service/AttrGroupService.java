package com.hgz.shop.product.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.product.entity.AttrGroupEntity;
import com.hgz.shop.product.vo.AttrGroupWithAttrsVo;
import com.hgz.shop.product.vo.SpuItemAttrGroup;
import com.hgz.shop.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 * @author firenay
 * @email 1046762075@qq.com
 * @date 2020-05-31 17:06:04
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryPage(Map<String, Object> params, Long catelogId);

	List<AttrGroupWithAttrsVo> getAttrGroupWithAttrByCatelogId(Long catelogId);

	List<SpuItemAttrGroup> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}
