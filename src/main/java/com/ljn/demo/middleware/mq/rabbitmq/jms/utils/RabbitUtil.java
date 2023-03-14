package com.ljn.demo.middleware.mq.rabbitmq.jms.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitUtil {
    private static ConnectionFactory factory;
    static {
        factory = new ConnectionFactory();
        factory.setHost("1.14.94.244");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("ems");
    }

    public static Connection getConnection() {
        try {
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection, Channel channel) {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
