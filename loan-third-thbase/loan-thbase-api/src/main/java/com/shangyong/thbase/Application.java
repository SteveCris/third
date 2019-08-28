package com.shangyong.thbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import com.shangyong.loan.autoconfigure.EnableExtRabbit;
import com.shangyong.loan.autoconfigure.EnableExtRedisCache;
import com.shangyong.loan.autoconfigure.EnableExtSleuth;
import com.shangyong.loan.autoconfigure.EnableExtWeb;

// 服务注册发现
@EnableDiscoveryClient
// 服务调用客户端注解
@EnableFeignClients(basePackages = { "com.shangyong.rest" })
// 服务熔断包含（@EnableCircuitBreaker）
@EnableHystrix
@EnableExtWeb
@EnableExtSleuth
@EnableExtRabbit
@EnableExtRedisCache
@EnableAspectJAutoProxy
@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = { "com.shangyong.thbase" })
@MapperScan("com.shangyong.thbase.dao")
public class Application extends SpringBootServletInitializer {

	/**
	 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.sources(this.getClass());
		return super.configure(builder);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
