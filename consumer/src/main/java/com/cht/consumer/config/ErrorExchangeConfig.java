package com.cht.consumer.config;

import com.example.constant.DirectConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  RabbitMQ 消息异常，重新发送到指定的交换机和路由键，以避免消息丢失
 */
@Configuration
public class ErrorExchangeConfig {

    // 声明队列
    @Bean
    public Queue errorQueue() {
        return new Queue(DirectConstant.ERROR_QUEUE_1);
    }


    // 声明交换机
    @Bean
    public DirectExchange errorExchange(){
        return new DirectExchange(DirectConstant.ERROR_EXCHANGE);
    }

    /**
     * 绑定队列 1 到交换机
     */
    @Bean
    public Binding errorBinding(){
        return BindingBuilder.bind(errorQueue()).to(errorExchange()).with(DirectConstant.ERROR_KEY);
    }

    /**
     * 处理 RabbitMQ 消息异常的 Spring Bean，它使用 RepublishMessageRecoverer 类将发送失败的消息重新发送到指定的交换机和路由键，以避免消息丢失
     */
    @Bean
    public MessageRecoverer recoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate,DirectConstant.ERROR_EXCHANGE,DirectConstant.ERROR_KEY);
    }
}
