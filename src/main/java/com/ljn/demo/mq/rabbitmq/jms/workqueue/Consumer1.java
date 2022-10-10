package com.ljn.demo.mq.rabbitmq.jms.workqueue;

import com.ljn.demo.mq.rabbitmq.jms.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("consume1===========" + new String(body));
                // 消息消费完成后手动确认
                // 参数1:消息的标识
                // 参数2:是否一次确认多个消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
