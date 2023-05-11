package com.cht.provider;

import com.example.constant.DirectConstant;
import com.example.constant.FanoutConstant;
import com.example.constant.QueueConstant;
import com.example.constant.TopicConstant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试：
 * 1、基础消息队列
 * 2、工作消息队列
 * 3、扇型交换机 (Fanout Exchange)
 * 4、直连交换机 (Direct Exchange)
 * 5、主题交换机 (Topic Exchange)
 */
@SpringBootTest
class QueueTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     *  基础消息队列
     */
    @Test
    void testSimplenessQueue() {
        // 消息
        String message = "hello world";
        /*
        * 参数 1：表示队列名称
        * 参数 2：表示消息
        */
        rabbitTemplate.convertAndSend(QueueConstant.QUEUE_1,message);
    }

    /**
     * 工作队列
     */
    @Test
    void workQueue() throws InterruptedException {
        String message = "hello work--";
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(QueueConstant.SIMPLENESSQUEUE,message + i);
            Thread.sleep(20);
        }
    }

    /**
     *  扇型交换机 (Fanout Exchange)
     */
    @Test
    void testFanoutExchange() {
        String message = "hello";
        rabbitTemplate.convertAndSend(FanoutConstant.EXCHANGE_FANOUT,"",message);
    }


    /**
     *  直连交换机 (Direct Exchange)
     */
    @Test
    void testDirectExchange() {
        String message = "hello";
        /*
         * 参数 1：交换机名称
         * 参数 2：key 值
         * 参数 3：消息
         */
        rabbitTemplate.convertAndSend(DirectConstant.EXCHANGE_DIRECT,DirectConstant.YELLOW,message);
    }

    /**
     *  主题交换机 (Topic Exchange)
     */
    @Test
    void testTopicExchange() {
        String message = "hello";
        rabbitTemplate.convertAndSend(TopicConstant.EXCHANGE_TOPIC,TopicConstant.FRONT_LR,message);
    }
}
