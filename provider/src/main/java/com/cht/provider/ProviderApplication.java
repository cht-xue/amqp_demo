package com.cht.provider;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    /**
     * 将 Spring AMQP 默认序列化方式，修改成 json 序列化
     */
    @Bean
    public MessageConverter json(){
        return new Jackson2JsonMessageConverter();
    }
}
