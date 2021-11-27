package com.hgz.shop.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hgz.shop.product.entity.PmsSkuInfoEntity;
import com.hgz.shop.product.service.PmsSkuInfoService;




/**
 * sku信息
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-12 21:46:26
 */
@RestController
@RequestMapping("product/pmsskuinfo")
public class PmsSkuInfoController {
    @Autowired
    private PmsSkuInfoService pmsSkuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsSkuInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId){
		PmsSkuInfoEntity pmsSkuInfo = pmsSkuInfoService.getById(skuId);

        return R.ok().put("pmsSkuInfo", pmsSkuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PmsSkuInfoEntity pmsSkuInfo){
		pmsSkuInfoService.save(pmsSkuInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PmsSkuInfoEntity pmsSkuInfo){
		pmsSkuInfoService.updateById(pmsSkuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] skuIds){
		pmsSkuInfoService.removeByIds(Arrays.asList(skuIds));

        return R.ok();
    }

}
