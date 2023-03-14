package com.ljn.demo.middleware.mq.rabbitmq.jms.routing;

import com.ljn.demo.middleware.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct", "direct");
        String routingKey = "info";
        channel.basicPublish("logs_direct", routingKey, null,
                ("direct type: " + routingKey).getBytes());
        RabbitUtil.close(connection, channel);
    }
}
