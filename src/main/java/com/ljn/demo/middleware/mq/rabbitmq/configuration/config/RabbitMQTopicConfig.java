package com.ljn.demo.middleware.mq.rabbitmq.configuration.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RabbitMQTopicConfig {
    public static final String QUEUE01 = "queue_topic01";
    public static final String QUEUE02 = "queue_topic02";
    public static final String EXCHANGE = "exchange_topic";
    public static final String ROUTINGKEY01 = "goods.detail.*";
    public static final String ROUTINGKEY02 = "order.#";

    @Bean
    public Queue queue01() {
        return new Queue(QUEUE01);
    }

    @Bean
    public Queue queue02() {
        return new Queue(QUEUE02);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding01(Queue queue01, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue01).to(topicExchange).with(ROUTINGKEY01);
    }

    @Bean
    public Binding binding02(Queue queue02, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue02).to(topicExchange).with(ROUTINGKEY02);
    }
}
