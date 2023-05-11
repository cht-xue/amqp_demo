package com.cht.consumer.listener;

import com.example.constant.DirectConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Direct 直接交换机类型消费者
 * 直接交换机将消息路由到路由键与绑定键完全匹配的队列中
 */
@Component
public class DirectListener {
    /**
     * Direct 消费者 1
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = DirectConstant.DIRECT_QUEUE_1),
            exchange = @Exchange(name = DirectConstant.EXCHANGE_DIRECT,type = ExchangeTypes.DIRECT),
            key = {DirectConstant.RED,DirectConstant.PINK}
    ))
    public void DirectQueueMassage1(String msg){
        System.out.println("Direct 消费者 1 接收消息：【" + msg  + "】");
    }

    /**
     * Direct 消费者 2
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = DirectConstant.DIRECT_QUEUE_2),
            exchange = @Exchange(name = DirectConstant.EXCHANGE_DIRECT,type = ExchangeTypes.DIRECT),
            key = {DirectConstant.RED,DirectConstant.YELLOW}
    ))
    public void DirectQueueMassage2(String msg){
        System.out.println("Direct 消费者 2 接收消息：【" + msg  + "】");
    }
}
