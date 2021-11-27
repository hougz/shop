package com.hgz.shop.product.service.impl;

import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hgz.shop.product.dao.PmsCommentReplayDao;
import com.hgz.shop.product.entity.PmsCommentReplayEntity;
import com.hgz.shop.product.service.PmsCommentReplayService;


@Service("pmsCommentReplayService")
public class PmsCommentReplayServiceImpl extends ServiceImpl<PmsCommentReplayDao, PmsCommentReplayEntity> implements PmsCommentReplayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsCommentReplayEntity> page = this.page(
                new Query<PmsCommentReplayEntity>().getPage(params),
                new QueryWrapper<PmsCommentReplayEntity>()
        );

        return new PageUtils(page);
    }

}