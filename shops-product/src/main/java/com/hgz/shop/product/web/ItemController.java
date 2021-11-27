package com.hgz.shop.product.web;

import com.hgz.shop.product.service.SkuInfoService;
import com.hgz.shop.product.vo.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/30 16:25
 */
@Controller
public class ItemController {

    @Autowired
    private SkuInfoService skuInfoService;

    @RequestMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model) throws ExecutionException, InterruptedException {

        SkuItemVo vo = skuInfoService.item(skuId);

        model.addAttribute("item", vo);
        return "item";
    }

}
