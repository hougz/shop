package com.hgz.shop.product.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.hgz.shop.product.entity.CategoryEntity;
import com.hgz.shop.product.vo.Catalog2Vo;
import com.hgz.shop.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> asList);

    /**
     * 找到该三级分类的完整路径
     * @param categorygId
     * @return
     */
    Long[] findCatelogPathById(Long categorygId);

    void updateCascade(CategoryEntity category);

    List<CategoryEntity> getLevel1Catagories();

    Map<String, List<Catalog2Vo>> getCategoryMap();

    Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisLock();

    Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisson();

    Map<String, List<Catalog2Vo>> getCatalogJsonDbWithSpringCache();


    List<CategoryEntity> getLevel1Categorys();

    Map<String,List<Catalog2Vo>> getCatalogJson();

}

