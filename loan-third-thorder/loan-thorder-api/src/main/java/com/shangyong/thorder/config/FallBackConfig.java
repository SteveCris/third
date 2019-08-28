package com.shangyong.thorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.rest.feign.MqCloudHystrixService;


@Configuration
public class FallBackConfig {
	
	@Bean
	public BaseCloudHystrixService.HystrixClientFallbackFactory baseCloudHystrixService() {
		return new BaseCloudHystrixService.HystrixClientFallbackFactory();
	}
	
	@Bean
	public MqCloudHystrixService.HystrixClientFallbackFactory mqCloudHystrixService() {
		return new MqCloudHystrixService.HystrixClientFallbackFactory();
	}
}
