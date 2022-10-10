package com.ljn.demo.mq.rabbitmq.jms.fanout;

import com.ljn.demo.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 将交换机与临时队列通过通道连接起来
        channel.queueBind(queueName, "logs", "");
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer2====" + new String(body));
            }
        });
    }
}
