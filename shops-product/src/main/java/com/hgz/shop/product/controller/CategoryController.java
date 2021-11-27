package com.hgz.shop.product.controller;

import com.hgz.shop.product.entity.CategoryEntity;
import com.hgz.shop.product.service.CategoryService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 商品三级分类列表
 *
 * @author hgz
 * @version 1.0
 * @date 2021/9/19 12:28
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 查询所有分类并将查到分类通过树状结构组装起来
     */
    @RequestMapping("/list/tree")
    public List<CategoryEntity> list(){
        List<CategoryEntity> categoryEntities = categoryService.listWithTree();

        return categoryEntities;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category){
        categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     * 级联更新所有数据
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category){
        categoryService.updateCascade(category);

        return R.ok();
    }

    /**
     * 批量修改
     * @param categorys
     * @return
     */
    @RequestMapping("/updateNodes")
    public R update(@RequestBody CategoryEntity[] categorys){
        categoryService.updateBatchById(Arrays.asList(categorys));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
        //删除之前需要判断待删除的菜单那是否被别的地方所引用。
        categoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok();
    }
}
