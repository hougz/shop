package com.hgz.shop.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hgz.shop.order.entity.OmsOrderOperateHistoryEntity;
import com.hgz.shop.order.service.OmsOrderOperateHistoryService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.R;



/**
 * 订单操作历史记录
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 09:46:56
 */
@RestController
@RequestMapping("order/omsorderoperatehistory")
public class OmsOrderOperateHistoryController {
    @Autowired
    private OmsOrderOperateHistoryService omsOrderOperateHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = omsOrderOperateHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OmsOrderOperateHistoryEntity omsOrderOperateHistory = omsOrderOperateHistoryService.getById(id);

        return R.ok().put("omsOrderOperateHistory", omsOrderOperateHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OmsOrderOperateHistoryEntity omsOrderOperateHistory){
		omsOrderOperateHistoryService.save(omsOrderOperateHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OmsOrderOperateHistoryEntity omsOrderOperateHistory){
		omsOrderOperateHistoryService.updateById(omsOrderOperateHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		omsOrderOperateHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
