package com.ljn.demo.middleware.mq.rabbitmq.configuration.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RabbitMQDirectConfig {
    public static final String QUEUE01 = "queue_direct01";
    public static final String QUEUE02 = "queue_direct02";
    public static final String EXCHANGE = "exchange_direct";
    public static final String ROUTINGKEY01 = "queue.red";
    public static final String ROUTINGKEY02 = "queue.blue";

    @Bean
    public Queue queue01() {
        return new Queue(QUEUE01);
    }

    @Bean
    public Queue queue02() {
        return new Queue(QUEUE02);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding01(Queue queue01, DirectExchange directExchange) {
        return BindingBuilder.bind(queue01).to(directExchange).with(ROUTINGKEY01);
    }

    // 一个交换机和一个队列之间可以通过不同的routingkey进行绑定
    @Bean
    public Binding binding03(Queue queue01, DirectExchange directExchange) {
        return BindingBuilder.bind(queue01).to(directExchange).with(ROUTINGKEY02);
    }

    @Bean
    public Binding binding02(Queue queue02, DirectExchange directExchange) {
        return BindingBuilder.bind(queue02).to(directExchange).with(ROUTINGKEY02);
    }
}
