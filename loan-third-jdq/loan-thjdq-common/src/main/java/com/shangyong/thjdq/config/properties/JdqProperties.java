package com.shangyong.thjdq.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的配置
 */
@Configuration
public class JdqProperties {

    @Bean
    @ConfigurationProperties(prefix = "jdq.key")
    public JdqKeyProperties jdqKeyProperties(){
        return new JdqKeyProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jdq.common")
    public JdqCommonProperties jdqCommonProperties(){
        return new JdqCommonProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "aliyun.oss")
    public AliyunOssProperties aliyunOssProperties(){
        return new AliyunOssProperties();
    }

}
