package com.hgz.shop.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/27 21:45
 */
@Configuration
public class MyRedisConfig {

    @Value("${ipAddr}")
    private String ip;


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://" + ip + ":6379");
        return Redisson.create(config);
    }
}
