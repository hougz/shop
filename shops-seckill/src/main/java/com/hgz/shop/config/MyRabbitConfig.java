package com.hgz.shop.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/30 18:17
 *
 * 配置序列化方式
 */
@Configuration
public class MyRabbitConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
