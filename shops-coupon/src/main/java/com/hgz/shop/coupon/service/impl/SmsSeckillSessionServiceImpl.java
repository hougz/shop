package com.hgz.shop.coupon.service.impl;

import com.hgz.shop.coupon.entity.SmsSeckillSkuRelationEntity;
import com.hgz.shop.coupon.service.SmsSeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.Query;

import com.hgz.shop.coupon.dao.SmsSeckillSessionDao;
import com.hgz.shop.coupon.entity.SmsSeckillSessionEntity;
import com.hgz.shop.coupon.service.SmsSeckillSessionService;


@Service("smsSeckillSessionService")
public class SmsSeckillSessionServiceImpl extends ServiceImpl<SmsSeckillSessionDao, SmsSeckillSessionEntity> implements SmsSeckillSessionService {

    private final String form="yyyy-MM-dd HH:mm:ss";

    @Autowired
    private SmsSeckillSkuRelationService skuRelationService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SmsSeckillSessionEntity> page = this.page(
                new Query<SmsSeckillSessionEntity>().getPage(params),
                new QueryWrapper<SmsSeckillSessionEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public List<SmsSeckillSessionEntity> getLates3DaySession() {
        List<SmsSeckillSessionEntity> list = this.list(new QueryWrapper<SmsSeckillSessionEntity>().between("start_time", startTime(), endTime()));
        if(list!=null&&list.size()>0){
            return list.stream().map(session->{
                Long id = session.getId();
                List<SmsSeckillSkuRelationEntity> entities = skuRelationService.list(new QueryWrapper<SmsSeckillSkuRelationEntity>().eq("promotion_session_id", id));
                session.setRelationSkus(entities);
                return session;
            }).collect(Collectors.toList());
        }
        return null;
    }

    private String startTime(){
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return start.format(DateTimeFormatter.ofPattern(form));
    }

    private String endTime(){
        LocalDateTime end = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.MAX);
        return end.format(DateTimeFormatter.ofPattern(form));
    }
}