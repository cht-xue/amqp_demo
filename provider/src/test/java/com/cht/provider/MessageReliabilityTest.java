package com.cht.provider;

import com.example.constant.DirectConstant;
import com.example.constant.QueueConstant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * 消息可靠性测试:
 * 1、生产者消息确认机制
 * 2、消息持久化
 */
@SpringBootTest
@Slf4j
public class MessageReliabilityTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     *  生产者消息确认机制 测试
     */
    @Test
    void test() {
        String message = "How are you doing";
        // 准备 CorrelationData 设置消息 ID
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 发送消息
        rabbitTemplate.convertAndSend(DirectConstant.EXCHANGE_DIRECT,DirectConstant.PINK,message,correlationData);
        // ConfirmCallback 监听消息确认回调方法
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            if (ack) {
                // 消息发送成功

                // 记录向交换机或队列发送消息成功日志
                log.info("消息已经被交换机成功接收，消息ID：{}", correlationData.getId());
            } else {
                // 消息发送失败

                // 记录向交换机或队列发送消息失败日志
                log.error("消息发送被交换机失败，消息ID：{}，原因：{}", correlationData.getId(), cause);
            }
        });
    }

    /**
     * 手动消息持久化
     * 高版本默认创建的交换机和队列是持久化的,没有队列与交换机绑定时交换机自动删除,消息也默认为持久化
     */
    @Test
    void test2() {
        String message = "How are you doing";
        rabbitTemplate.convertAndSend(QueueConstant.QUEUE_2,message,message1 -> {
            MessageProperties properties = message1.getMessageProperties();
            properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT); // 设置消息持久化
            return message1;
        });
    }

}
