package com.ljn.demo.middleware.mq.rabbitmq.jms.helloword;

import com.ljn.demo.middleware.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        // 一般在消费者中不用声明队列，因为生产者中已经声明了，如果要声明，则要保证与生产者的声明参数相同
        // channel.queueDeclare("hello", false, false, false, null);
        // 每次消息队列发给消费者的消息的个数
        channel.basicQos(1);
        // 消费消息
        // 参数1:队列名称
        // 参数2:消费者收到消息会向队列发送确认消息（并不是处理完消息），队列会把消息删除，可能会造成消息丢失
        // 参数3:消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            // body:消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息: " + new String(body));
            }
        });
        // 一般会让消费者一值监听，所以不用关闭
    }
}
