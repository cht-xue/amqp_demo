package com.cht.consumer.config;

import com.example.constant.FanoutConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明 Fanout 交换机类型和队列，并绑定
 * 为声明绑定方式一，
 * 方式二注解式声明绑定：就是在消费者上的 @RabbitListener 注解中使用参数声明并绑定
 */
@Configuration
public class FanoutConfig {

    // 声明队列
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FanoutConstant.FANOUT_QUEUE_1);
    }


    // 声明交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FanoutConstant.EXCHANGE_FANOUT);
    }

    /**
     * 绑定队列 1 到交换机
     */
    @Bean
    public Binding fanoutBinding(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

}
