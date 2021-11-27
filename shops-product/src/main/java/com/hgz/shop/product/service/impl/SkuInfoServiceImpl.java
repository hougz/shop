package com.hgz.shop.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgz.shop.product.dao.PmsSkuInfoDao;
import com.hgz.shop.product.entity.PmsSkuImagesEntity;
import com.hgz.shop.product.entity.PmsSkuInfoEntity;
import com.hgz.shop.product.entity.PmsSpuInfoDescEntity;
import com.hgz.shop.product.service.PmsSkuImagesService;
import com.hgz.shop.product.service.PmsSkuSaleAttrValueService;
import com.hgz.shop.product.service.PmsSpuInfoDescService;
import com.hgz.shop.product.service.SkuInfoService;
import com.hgz.shop.product.vo.SkuItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/30 16:32
 */
@Slf4j
@Service
public class SkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoDao, PmsSkuInfoEntity> implements SkuInfoService {

    @Autowired
    private PmsSkuImagesService imagesService;

    @Autowired
    private PmsSpuInfoDescService spuInfoDescService;

    @Autowired
    private PmsSkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 自定义线程串池
     */
    @Autowired
    private ThreadPoolExecutor executor;


    @Override
    public SkuItemVo item(Long skuId) {
        try {
            SkuItemVo skuItemVo = new SkuItemVo();

            CompletableFuture<PmsSkuInfoEntity> skuInfo = CompletableFuture.supplyAsync(() -> {
                //1 sku基本信息
                PmsSkuInfoEntity info = getById(skuId);
                skuItemVo.setInfo(info);
                return info;
            }, executor);


            CompletableFuture<Void> image = CompletableFuture.runAsync(() -> {
                //2 sku图片信息
                List<PmsSkuImagesEntity> images = imagesService.getImageBySkuId(skuId);
                skuItemVo.setImages(images);
            }, executor);


            //3 获取spu销售属性组合


            //4 获取spu介绍   此处依赖1 sku基本信息 需要1执行完再执行
            CompletableFuture<Void> spu = skuInfo.thenAcceptAsync(res -> {
                Long spuId = res.getSpuId();
                PmsSpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(spuId);
                skuItemVo.setDesc(spuInfoDescEntity);
            }, executor);

            //5 获取spu规格参数信息

            // 6.查询当前sku是否参与秒杀优惠


            // 等待所有任务都完成再返回
            // 等待所有任务都完成再返回
            CompletableFuture.allOf(image,spu).get();
            return skuItemVo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
