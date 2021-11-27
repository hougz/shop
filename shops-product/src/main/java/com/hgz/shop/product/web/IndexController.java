package com.hgz.shop.product.web;

import com.hgz.shop.product.entity.CategoryEntity;
import com.hgz.shop.product.service.CategoryService;
import com.hgz.shop.product.vo.Catalog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/23 14:58
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        model.addAttribute("categorys",categoryEntities);
        return "index";
    }

    @ResponseBody
    @GetMapping("index/catalog.json")
    public Map<String, List<Catalog2Vo>> getJson(){
        Map<String, List<Catalog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }
}
