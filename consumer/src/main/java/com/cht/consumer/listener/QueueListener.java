package com.cht.consumer.listener;

import com.example.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 普通队列消费者
 */
@Component
public class QueueListener {

    /**
     * 消费者
     * @param msg 消息
     */
    @RabbitListener(queues = QueueConstant.QUEUE_1)
    public void SimplenessQueue(String msg){
        System.out.println("普通消费者 1 接收消息：【" + msg  + "】");
    }
    @RabbitListener(queues = QueueConstant.QUEUE_2)
    public void SimplenessQueue2(String msg){
        System.out.println("普通消费者 2 接收消息：【" + msg  + "】");
    }


    /**
     * 处理同一个消息，工作队列
     * @param msg 消息
     */
    @RabbitListener(queues = QueueConstant.SIMPLENESSQUEUE)
    public void SimplenessQueueMassage(String msg) throws InterruptedException {
        System.out.println("消费者 1 接收消息：【" + msg  + "】");
        Thread.sleep(10);
    }
    @RabbitListener(queues = QueueConstant.SIMPLENESSQUEUE)
    public void SimplenessQueueMassage2(String msg) throws InterruptedException {
        System.out.println("消费者 2 接收消息：【" + msg  + "】");
        Thread.sleep(100);
    }
}
