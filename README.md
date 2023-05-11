# AMQP-Demo

## 介绍

这是一个使用 springboot 3 + AMQP 实现 rabbimq 的 Demo

> spring AMQP 官网：[Spring AMQP](https://spring.io/projects/spring-amqp) 

## 技术栈-环境

> 环境

- JDK 17
- maven 3.6.1
- RabbitMQ 3.11.11

> 技术栈

| 技术         | 说明                                      |
| ------------ | ----------------------------------------- |
| springboot 3 | 集成框架                                  |
| spring amqp  | Spring Boot 对 AMQP 的支持(包括 rabbitmq) |

## 功能

- 实现基础消息队列，工作消息队列
- 实现 Fanout 交换机类型
- 实现 Direct 交换机类型
- 实现 Topic 交换机类型

## 使用

修改 application.yml 配置文件中的 host,port,username,password 改成自己的

```
  rabbitmq:
    host: xx # rabbitmq 的 ip 地址
    port: 5672 # 端口
    username: guest # 账号
    password: guest # 密码
```

需要先启动 consumer 消费者，创建队列和交换机

## 包结构

```
common -- 公共包
consumer -- 消费者
provider -- 生成者
```

> common 包结构

```
src/main/java/com/example
	|-- constant -- 常量包
```

> consumer 消费者包结构

```
src/main
	|-- java/com/cht/consumer
		|-- config -- 配置队列和交换机
		|-- listener -- 消费者处理消息
		|-- ConsumerApplication -- 启动类
	|-- resources
		|-- application.yml -- 配置文件
```

> provider 生产者包结构

```
src
 |-- main
 	|-- java/com/cht/provider
 		|-- ProviderApplication -- 启动类
 	|-- resources
 		|-- application.yml -- 配置文件
 |-- test/java/com/cht/
 	| -- QueueTest -- 测试类
```