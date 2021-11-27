package com.hgz.shop.feign;

import com.hgz.shop.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/27 20:40
 */
@FeignClient("shops-coupon")
public interface CouponFeignService {


    @GetMapping("/coupon/smsseckillsession/seckillSkuLatest3Day")
    R getLate3DaySession();
}
