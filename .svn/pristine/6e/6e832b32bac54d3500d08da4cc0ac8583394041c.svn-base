package com.shangyong.thjdq.config;

import com.shangyong.backend.fegin.ApplicationCloudHystrixService;
import com.shangyong.backend.fegin.BlacklistCloudHystrixService;
import com.shangyong.backend.fegin.ReportCloudHystrixService;
import com.shangyong.backend.fegin.UploadCloudHystrixService;
import com.shangyong.rest.feign.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自己添加bean加深印象，自己可以处理服务降级、熔断逻辑
 * Created by zbb on 2019-03-15.
 */
@Configuration
public class FallbackConfig {

    @Bean
    public OrderCloudHystrixService.HystrixClientFallbackFactory orderCloudHystrixServiceFallbackFactory() {
        return new OrderCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public MqCloudHystrixService.HystrixClientFallbackFactory mqCloudHystrixServiceFallbackFactory() {
        return new MqCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public ApplicationCloudHystrixService.HystrixClientFallbackFactory applicationCloudHystrixServiceFallbackFactory() {
        return new ApplicationCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public UploadCloudHystrixService.HystrixClientFallbackFactory uploadCloudHystrixServiceFallbackFactory() {
        return new UploadCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public OrderBankCloudHystrixService.HystrixClientFallbackFactory orderBankCloudHystrixServiceFallbackFactory() {
        return new OrderBankCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public OrderRepaymentCloudHystrixService.HystrixClientFallbackFactory orderRepaymentCloudHystrixServiceFallbackFactory() {
        return new OrderRepaymentCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public BaseCloudHystrixService.HystrixClientFallbackFactory baseCloudHystrixServiceFallbackFactory() {
        return new BaseCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public OrderBorrowCloudHystrixService.HystrixClientFallbackFactory orderBorrowCloudHystrixServiceFallbackFactory() {
        return new OrderBorrowCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public BlacklistCloudHystrixService.HystrixClientFallbackFactory blacklistCloudHystrixServiceFallbackFactory() {
        return new BlacklistCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public BaseUserCloudHystrixService.HystrixClientFallbackFactory baseUserCloudHystrixServiceFallbackFactory() {
        return new BaseUserCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public ReportCloudHystrixService.HystrixClientFallbackFactory reportCloudHystrixServiceFallbackFactory() {
        return new ReportCloudHystrixService.HystrixClientFallbackFactory();
    }

    @Bean
    public OrderUserCloudHystrixService.HystrixClientFallbackFactory orderUserCloudHystrixServiceFallbackFactory() {
        return new OrderUserCloudHystrixService.HystrixClientFallbackFactory();
    }

}
