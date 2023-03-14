package com.ljn.demo.middleware.mq.rabbitmq.jms.topics;

import com.ljn.demo.middleware.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String queue = channel.queueDeclare().getQueue();
        String routingKey = "user.*";
        channel.queueBind(queue, "topics", routingKey);
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer1: " + new String(body));
            }
        });
    }
}
