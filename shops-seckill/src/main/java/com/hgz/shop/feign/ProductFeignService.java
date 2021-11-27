package com.hgz.shop.feign;

import com.hgz.shop.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/27 21:50
 */
@FeignClient("shops-product")
public interface ProductFeignService {


    @RequestMapping("/product/pmsskuinfo/info/{skuId}")
    R skuInfo(@PathVariable("skuId") Long skuId);
}
