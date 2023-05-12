package com.cht.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 开启消息退回机制，在 RabbitTemplate 对象中可以设置一个 ReturnsCallback 回调来处理退回的消息。
 * 该回调函数被调用时，会将消息原样返回到生产者
 */
@Configuration
@Slf4j
public class RabbitMQConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获取 RabbitTemplate 对象
        RabbitTemplate bean = applicationContext.getBean(RabbitTemplate.class);
        // 配置 ReturnsCallback
        bean.setReturnsCallback(r -> {
            // 记录交换机向队列发送失败日志
            log.error("消息发送队列失败，响应码: {},失败原因: {},交换机: {},路由 key: {},消息: {}",
                    r.getReplyCode(),r.getReplyText(),r.getExchange(),r.getRoutingKey(),r.getMessage().toString());
        });
    }
}
