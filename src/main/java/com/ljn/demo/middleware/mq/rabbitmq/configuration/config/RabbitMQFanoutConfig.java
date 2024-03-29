package com.ljn.demo.middleware.mq.rabbitmq.configuration.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RabbitMQFanoutConfig {
    private static final String QUEUE01 = "queue_fanout01";
    private static final String QUEUE02 = "queue_fanout02";
    private static final String EXCHANGE = "exchange_fanout";

    @Bean
    public Queue queue() {
        return new Queue("queue", true);
    }

    @Bean
    public Queue queue01() {
        return new Queue(QUEUE01);
    }

    @Bean
    public Queue queue02() {
        return new Queue(QUEUE02);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE);
    }

    @Bean
    public Binding binding01(Queue queue01, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue01).to(fanoutExchange);
    }

    @Bean
    public Binding binding02(Queue queue02) {
        return BindingBuilder.bind(queue02).to(fanoutExchange());
    }
}
