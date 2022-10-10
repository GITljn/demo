package com.ljn.demo.mq.rabbitmq.annotation.helloword;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// 监听hello队列
@RabbitListener(queuesToDeclare = @Queue(value = "hello", durable = "false", autoDelete = "true"))
public class HelloConsumer {
    // 处理消息的回调函数
    @RabbitHandler
    public void receive(String message) {
        System.out.println("message: " + message);
    }
}
