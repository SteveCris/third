package com.shangyong.thzlqb;

import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.shangyong.loan.autoconfigure.EnableExtModule;
import com.shangyong.loan.autoconfigure.EnableExtRabbit;
import com.shangyong.loan.autoconfigure.EnableExtRedisCache;
import com.shangyong.loan.autoconfigure.EnableExtSleuth;
import com.shangyong.loan.autoconfigure.EnableExtWeb;

// 服务注册发现
@EnableDiscoveryClient
// 服务调用客户端注解
@EnableFeignClients(basePackages = { "com.shangyong.rest", "com.shangyong.backend.fegin" })
// 服务熔断包含（@EnableCircuitBreaker）
@EnableHystrix
@EnableExtWeb
@EnableExtSleuth
@EnableExtRabbit
@EnableExtRedisCache
@EnableExtModule
@EnableAspectJAutoProxy
@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = { "com.shangyong.thzlqb" })
@MapperScan("com.shangyong.thzlqb.dao")
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

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ResponseErrorHandler() {

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return true;
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				// nothing
			}
		});
		return restTemplate;
	}
}
