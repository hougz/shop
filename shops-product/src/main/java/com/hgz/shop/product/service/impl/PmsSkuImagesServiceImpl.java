package com.hgz.shop.product.service.impl;

import com.hgz.shop.product.entity.CategoryEntity;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hgz.shop.product.dao.PmsSkuImagesDao;
import com.hgz.shop.product.entity.PmsSkuImagesEntity;
import com.hgz.shop.product.service.PmsSkuImagesService;


@Service("pmsSkuImagesService")
public class PmsSkuImagesServiceImpl extends ServiceImpl<PmsSkuImagesDao, PmsSkuImagesEntity> implements PmsSkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSkuImagesEntity> page = this.page(
                new Query<PmsSkuImagesEntity>().getPage(params),
                new QueryWrapper<PmsSkuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PmsSkuImagesEntity> getImageBySkuId(Long skuId) {
        List<PmsSkuImagesEntity> skuImagesEntity = baseMapper.selectList(new QueryWrapper<PmsSkuImagesEntity>().eq("sku_id",skuId));
        return skuImagesEntity;
    }
}