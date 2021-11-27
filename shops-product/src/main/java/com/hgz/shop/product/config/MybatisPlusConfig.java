package com.hgz.shop.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/9/12 21:40
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 解决MybatisPlus 分页 总记录数，总页数为0
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        return page;
    }
}
