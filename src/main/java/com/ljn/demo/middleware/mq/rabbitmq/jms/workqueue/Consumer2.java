package com.ljn.demo.middleware.mq.rabbitmq.jms.workqueue;

import com.ljn.demo.middleware.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consume2===========" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
