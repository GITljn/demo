package com.ljn.demo.middleware.mq.rabbitmq.jms.workqueue;

import com.ljn.demo.middleware.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer3 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consume3===========" + new String(body));
            }
        });
    }
}
