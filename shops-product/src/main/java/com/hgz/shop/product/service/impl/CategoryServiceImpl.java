package com.hgz.shop.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgz.shop.product.dao.CategoryDao;
import com.hgz.shop.product.entity.CategoryEntity;
import com.hgz.shop.product.service.CategoryService;
import com.hgz.shop.product.vo.Catalog2Vo;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> entities = baseMapper.selectList(null);
        List<CategoryEntity> collect = entities.stream()
                .filter(item -> item.getParentCid() == 0)
                .map(menu -> {
                    menu.setChildren(getChildrens(menu, entities));
                    return menu;
                })
                .sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {

    }

    @Override
    public Long[] findCatelogPathById(Long categorygId) {
        return new Long[0];
    }

    /**
     * 级联更新所有数据
     *
     * @param category
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        if (!StringUtils.isEmpty(category.getName())) {
            //categoryBrandRelationService.updateCategory(category);
        }
    }

    @Override
    public List<CategoryEntity> getLevel1Catagories() {
        return null;
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCategoryMap() {
        return null;
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisLock() {
        return null;
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisson() {
        return null;
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJsonDbWithSpringCache() {
        return null;
    }

    private List<CategoryEntity> getChildrens(CategoryEntity categoryEntity, List<CategoryEntity> entities) {
        List<CategoryEntity> collect = entities.stream()
                .filter(item -> item.getParentCid() == categoryEntity.getCatId())
                .map(menu -> {
                    menu.setChildren(getChildrens(menu, entities));
                    return menu;
                })
                .sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());
        return collect;
    }


    /**
     * 查询一级分类
     *
     * @return
     */
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
    }




    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        String category = stringRedisTemplate.opsForValue().get("category");
        if(StringUtils.isEmpty(category)){
            Map<String, List<Catalog2Vo>> data = getData();
            stringRedisTemplate.opsForValue().set("category", JSON.toJSONString(data));
            return data;
        }
        Map<String, List<Catalog2Vo>> listMap = JSON.parseObject(category, new TypeReference<Map<String, List<Catalog2Vo>>>() {
        });
        log.info("缓存获取");
        return listMap;
    }

    private Map<String, List<Catalog2Vo>> getData(){
        List<CategoryEntity> level1Categorys =baseMapper.selectList(null);
        Map<String, List<Catalog2Vo>> parent_cid=level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //查到一级分类下的二级分类
            List<CategoryEntity> categoryEntities = getParentCid(level1Categorys,v.getCatId());
            List<Catalog2Vo> catalog2VoList=null;
            if (categoryEntities != null) {
                catalog2VoList=categoryEntities.stream().map(item -> {
                    Catalog2Vo catalog2Vo = new Catalog2Vo(v.getCatId().toString(), null, item.getCatId().toString(), item.getName());
                    //查到二级分类下的三级分类
                    List<CategoryEntity> categoryEntityList = getParentCid(level1Categorys,item.getCatId());
                    if(categoryEntityList!=null){
                        List<Catalog2Vo.Catalog3Vo> collect=categoryEntityList.stream().map(l3->{
                            Catalog2Vo.Catalog3Vo catalog3Vo = new Catalog2Vo.Catalog3Vo(item.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catalog3Vo;
                        }).collect(Collectors.toList());
                        catalog2Vo.setCatalog3List(collect);
                    }
                    return catalog2Vo;
                }).collect(Collectors.toList());
            }
            return catalog2VoList;
        }));
        return parent_cid;
    }


  private  List<CategoryEntity> getParentCid(List<CategoryEntity> level1Categorys,Long parentId){
      List<CategoryEntity> collect = level1Categorys.stream().filter(item ->
              item.getParentCid()==parentId).collect(Collectors.toList());
      return collect;
  }
}