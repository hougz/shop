package com.hgz.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/24 9:53
 */

/**
 * 定时任务配置
 */
@EnableAsync
@Configuration
@EnableScheduling
public class ScheduledConfig {
}
