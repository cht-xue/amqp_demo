package com.cht.consumer.listener;

import com.example.constant.FanoutConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Fanout 扇形交换机类型消费者
 * 扇形交换机将消息广播到与它绑定的所有队列中
 */
@Component
public class FanoutListener {

    /**
     * Fanout 消费者 1
     * @param msg 消息
     */
    @RabbitListener(queues = FanoutConstant.FANOUT_QUEUE_1)
    public void SimplenessQueueMassage1(String msg){
        System.out.println("Fanout 消费者 1 接收消息：【" + msg  + "】");
    }

    /**
     * Fanout 消费者 2,使用 @RabbitListener 注解式声明绑定
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = FanoutConstant.FANOUT_QUEUE_2),
            exchange = @Exchange(name = FanoutConstant.EXCHANGE_FANOUT,type = ExchangeTypes.FANOUT)))
    public void SimplenessQueueMassage2(String msg){
        System.out.println("Fanout 消费者 2 接收消息：【" + msg  + "】");
    }
}
