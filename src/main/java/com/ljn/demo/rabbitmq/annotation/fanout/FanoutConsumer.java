package com.ljn.demo.rabbitmq.annotation.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 无参数表示临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")
            )
    })
    public void receive1(String message) {
        System.out.println("message1: " + message);
    }

//    @RabbitListener(bindings = {
//            @QueueBinding(
//                    value = @Queue, // 无参数表示临时队列
//                    exchange = @Exchange(value = "logs", type = "fanout")
//            )
//    })
//    public void receive2(String message) {
//        System.out.println("message2: " + message);
//    }
}
