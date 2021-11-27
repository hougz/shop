package com.hgz.shop.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hgz.shop.coupon.entity.UndoLogEntity;
import com.hgz.shop.coupon.service.UndoLogService;
import com.hgz.shop.utils.PageUtils;
import com.hgz.shop.utils.R;



/**
 * 
 *
 * @author hgz
 * @email hgz@163.com
 * @date 2021-09-19 10:02:46
 */
@RestController
@RequestMapping("coupon/undolog")
public class UndoLogController {
    @Autowired
    private UndoLogService undoLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = undoLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		UndoLogEntity undoLog = undoLogService.getById(id);

        return R.ok().put("undoLog", undoLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UndoLogEntity undoLog){
		undoLogService.save(undoLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UndoLogEntity undoLog){
		undoLogService.updateById(undoLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		undoLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
