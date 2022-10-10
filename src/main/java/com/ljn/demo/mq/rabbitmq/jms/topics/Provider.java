package com.ljn.demo.mq.rabbitmq.jms.topics;

import com.ljn.demo.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String routingKey = "user";
        channel.basicPublish("topics", routingKey, null, "topics type".getBytes());
        RabbitUtil.close(connection, channel);
    }
}
