package com.ljn.demo.middleware.mq.rabbitmq.jms.workqueue;

import com.ljn.demo.middleware.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "work", null, ("work queue" + i).getBytes());
        }
        RabbitUtil.close(connection, channel);
    }
}
