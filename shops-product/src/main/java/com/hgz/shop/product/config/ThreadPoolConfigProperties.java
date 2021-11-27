package com.hgz.shop.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title: ThreadPoolConfigProperties</p>
 * Description：
 *
 */
@ConfigurationProperties(prefix = "glmall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {

	private Integer coreSize;

	private Integer maxSize;

	private Integer keepAliveTime;
}
