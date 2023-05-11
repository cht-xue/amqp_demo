package com.cht.consumer.config;

import com.example.constant.QueueConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明基础消息队列
 */
@Configuration
public class QueueConfig {

    /**
     * 声明一个消息队列
     */
    @Bean
    public Queue queue() {
        return new Queue(QueueConstant.SIMPLENESSQUEUE);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QueueConstant.QUEUE_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QueueConstant.QUEUE_2);
    }

}
