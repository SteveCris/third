package com.shangyong.thjdq.config;

import com.shangyong.thjdq.constants.JdqConstant;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置rabbitmq基本参数
 * Created by zhengbb on 2019-05-21.
 */
@Configuration
public class RabbitListenerConfig {

    @Bean("rabbitTaskContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        // 每次一次性从broker里面取的待消费的消息的个数
        factory.setPrefetchCount(JdqConstant.RABBITMQ_DEFAULT_PREFETCH_COUNT);
        // 每个listener并发消费者个数
        factory.setConcurrentConsumers(JdqConstant.RABBITMQ_DEFAULT_CONCURRENT);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean("rabbitTask2ContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitTask2ContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        // 每次一次性从broker里面取的待消费的消息的个数
        factory.setPrefetchCount(JdqConstant.RABBITMQ_DEFAULT_PREFETCH_COUNT);
        // 每个listener并发消费者个数
        factory.setConcurrentConsumers(JdqConstant.RABBITMQ_DEFAULT_CONCURRENT_2);
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
