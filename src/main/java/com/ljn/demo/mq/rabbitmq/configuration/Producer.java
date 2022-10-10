package com.ljn.demo.mq.rabbitmq.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendSeckillMsg(String msg) {
        log.info("发送消息: " + msg);
        rabbitTemplate.convertAndSend("seckillExchange", "seckill.seckillMsg", msg);
    }

    public void send(Object msg) {
        log.info("发送消息: " + msg);
        rabbitTemplate.convertAndSend("queue", msg);
    }

    public void send_fanout(Object msg) {
        log.info("发送消息: " + msg);
        rabbitTemplate.convertAndSend("exchange_fanout", "", msg);
    }

    public void send_direct_red(Object msg) {
        log.info("发送red消息: " + msg);
        rabbitTemplate.convertAndSend("exchange_direct", "queue.red", msg);
    }

    public void send_direct_blue(Object msg) {
        log.info("发送blue消息: " + msg);
        rabbitTemplate.convertAndSend("exchange_direct", "queue.blue", msg);
    }

    public void send_topic01(Object msg) {
        log.info("发送topic01消息: " + msg);
        rabbitTemplate.convertAndSend("exchange_topic", "goods.detail.1", msg);
    }

    public void send_topic02(Object msg) {
        log.info("发送topic02消息: " + msg);
        rabbitTemplate.convertAndSend("exchange_topic", "order.detail.2", msg);
    }
}
