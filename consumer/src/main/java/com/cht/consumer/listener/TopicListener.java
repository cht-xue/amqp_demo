package com.cht.consumer.listener;

import com.example.constant.TopicConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic 主题交换机类型消费者
 * 主题交换机将消息路由到一组符合特定模式的队列中。主题交换机支持通配符（*和#）匹配
 */
@Component
public class TopicListener {
    /**
     * Topic 消费者 1
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = TopicConstant.TOPIC_QUEUE_1),
            exchange = @Exchange(name = TopicConstant.EXCHANGE_TOPIC,type = ExchangeTypes.TOPIC),
            key = {TopicConstant.FRONT_CHT,TopicConstant.REAR_CHT}
    ))
    public void TopicQueueMassage1(String msg){
        System.out.println("Topic 消费者 1 接收消息：【" + msg  + "】");
    }

    /**
     * Topic 消费者 2
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = TopicConstant.TOPIC_QUEUE_2),
            exchange = @Exchange(name = TopicConstant.EXCHANGE_TOPIC,type = ExchangeTypes.TOPIC),
            key = {TopicConstant.FRONT_LR,TopicConstant.REAR_LR}
    ))
    public void TopicQueueMassage2(String msg){
        System.out.println("Topic 消费者 2 接收消息：【" + msg  + "】");
    }
}
