package com.hgz.shop.controller;

import com.hgz.shop.service.SeckillService;
import com.hgz.shop.to.SeckillSkuRedisTo;
import com.hgz.shop.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/30 15:24
 */
@Slf4j
@Controller
@RequestMapping("/seckill")
public class SeckillController {


    @Autowired
    private SeckillService seckillService;


    @ResponseBody
    @GetMapping("/currentSeckillSkus")
    public R getSeckill(){
        log.info("获取秒杀商品");
        List<SeckillSkuRedisTo> vos =seckillService.getSeckill();
        return R.ok().setData(vos);
    }

    @ResponseBody
    @GetMapping("/sku/seckill/{skuId}")
    public R getSkuSeckillInfo(@PathVariable("skuId") Long skuId){
        SeckillSkuRedisTo to = seckillService.getSkuSeckillInfo(skuId);
        return R.ok().setData(to);
    }

    @GetMapping("/kill")
    public String secKill(@RequestParam("killId") String killId, @RequestParam("key") String key, @RequestParam("num") Integer num, Model model){
        String orderSn = seckillService.kill(killId,key,num);
        // 1.判断是否登录
        model.addAttribute("orderSn", orderSn);
        return "success";
    }

}
