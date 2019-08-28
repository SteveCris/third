package com.shangyong.thjdq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 白名单配置类
 * Created by zhengbb on 2019-07-01.
 */
//@Configuration
//@ConditionalOnProperty(name="spring.profiles.active", havingValue ="product")
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSecurityInterceptor())
                .excludePathPatterns("/job/**")
                .excludePathPatterns("/thjdq/job/**")
                .excludePathPatterns("/risk/**")
                .excludePathPatterns("/thjdq/risk/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/thjdq/error")
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
