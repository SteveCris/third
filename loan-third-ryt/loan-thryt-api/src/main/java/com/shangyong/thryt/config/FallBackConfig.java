package com.shangyong.thryt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shangyong.backend.fegin.ApplicationCloudHystrixService;
import com.shangyong.backend.fegin.ReportCloudHystrixService;
import com.shangyong.backend.fegin.UploadCloudHystrixService;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.rest.feign.BaseUserCloudHystrixService;
import com.shangyong.rest.feign.MqCloudHystrixService;
import com.shangyong.rest.feign.OrderBankCloudHystrixService;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.rest.feign.ThirdInfoPushHystrixService;

@Configuration
public class FallBackConfig {

	@Bean
	public OrderCloudHystrixService.HystrixClientFallbackFactory orderCloudHystrixServiceFallbackFactory() {
		return new OrderCloudHystrixService.HystrixClientFallbackFactory();
	}

	@Bean
	public ReportCloudHystrixService.HystrixClientFallbackFactory reportCloudHystrixServiceFallbackFactory() {
		return new ReportCloudHystrixService.HystrixClientFallbackFactory();
	}

	@Bean
	public UploadCloudHystrixService.HystrixClientFallbackFactory uploadCloudHystrixServiceFallbackFactory() {
		return new UploadCloudHystrixService.HystrixClientFallbackFactory();
	}

	@Bean
	public ApplicationCloudHystrixService.HystrixClientFallbackFactory applicationCloudHystrixServiceFallbackFactory() {
		return new ApplicationCloudHystrixService.HystrixClientFallbackFactory();
	}

	@Bean
	public MqCloudHystrixService.HystrixClientFallbackFactory mqCloudHystrixServiceFallbackFactory() {
		return new MqCloudHystrixService.HystrixClientFallbackFactory();
	}

	@Bean
	public ThirdInfoPushHystrixService.ThirdInfoPushFallbackFactory thirdInfoPushHystrixServiceFallbackFactory() {
		return new ThirdInfoPushHystrixService.ThirdInfoPushFallbackFactory();
	}

	@Bean
	public OrderBankCloudHystrixService.HystrixClientFallbackFactory orderBankCloudHystrixServiceFallbackFactory() {
		return new OrderBankCloudHystrixService.HystrixClientFallbackFactory();
	}

	@Bean
	public BaseUserCloudHystrixService.HystrixClientFallbackFactory baseUserCloudHystrixServiceFallbackFactory() {
		return new BaseUserCloudHystrixService.HystrixClientFallbackFactory();
	}

	@Bean
	public BaseCloudHystrixService.HystrixClientFallbackFactory baseCloudHystrixService() {
		return new BaseCloudHystrixService.HystrixClientFallbackFactory();
	}

}
