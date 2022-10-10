package com.ljn.demo.mq.rabbitmq.jms.fanout;

import com.ljn.demo.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        // 不再是声明队列，而是声明交换机
        // 参数1:交换机名称
        // 参数2:交换机类型，fanout为广播类型
        channel.exchangeDeclare("logs", "fanout");
        channel.basicPublish("logs", "", null, "fanout type".getBytes());
        RabbitUtil.close(connection, channel);
    }
}
