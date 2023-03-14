package com.ljn.demo.middleware.mq.rabbitmq.jms.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.创建提供rabbitmq连接的工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 2.设置rabbitmq所在的ip和port
        factory.setHost("1.14.94.244");
        factory.setPort(5672);
        // 3.设置准备连接的虚拟主机的名称
        factory.setVirtualHost("/ems");
        // 4.设置访问虚拟主机的用户名和密码
        factory.setUsername("ems");
        factory.setPassword("ems");

        // 5.从连接工厂中获取连接
        Connection connection = factory.newConnection();
        // 6.从连接中得到通道
        Channel channel = connection.createChannel();
        // 7.声明并绑定队列（一个通道可以绑定多个队列）
        // 参数1:队列名称，没有队列则自动创建
        // 参数2:队列是否需要持久化（并不会持久化消息），如果不持久化，则rabbitmq重启后该队列就会消失
        // 参数3:是否独占消息队列
        // 参数4:是否在队列中最后一个消息被消费并与消费者断开连接后，删除队列
        // 参数5:额外参数
        channel.queueDeclare("hello", false, false, false, null);
        // 8.利用通道向队列中发布消息
        // 参数1:交换机，helloworld模式下不需要交换机
        // 参数2:队列名
        // 参数3:额外设置。设置消息持久化MessageProperties.PERSISTENT_TEXT_PLAIN
        // 参数4:消息的具体内容
        channel.basicPublish("", "hello", null, "hello rabbitmq".getBytes());

        channel.close();
        connection.close();
    }
}
