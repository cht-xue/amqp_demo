package com.example.constant;

/**
 *  定义 Topic 主题交换机类型常量
 */
public class TopicConstant {

    // Topic 类型交换机
    public static final String EXCHANGE_TOPIC = "exchange.Topic";
    public static final String TOPIC_QUEUE_1 = "Topic.queue.1";
    public static final String TOPIC_QUEUE_2 = "Topic.queue.2";

    // Topic 类型所需主题
    public static final String FRONT_CHT = "cht.#";
    public static final String REAR_CHT = "#.cht";
    public static final String FRONT_LR = "lr.#";
    public static final String REAR_LR = "#.lr";
}
