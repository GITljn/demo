package com.ljn.demo.middleware.mq.rabbitmq.annotation.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Provider {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void testHelloWorld() {
        // convert: 将字符串转换成byte
        rabbitTemplate.convertAndSend("hello", "hello world 模型");
    }


    public void testWorkQueue() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work模型");
        }
    }


    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "fanout模型");
    }


    public void testRouting() {
        rabbitTemplate.convertAndSend("directs", "info", "route模型");
    }


    public void testTopic() {
        rabbitTemplate.convertAndSend("topics", "user.save", "topic模型");
    }

}
