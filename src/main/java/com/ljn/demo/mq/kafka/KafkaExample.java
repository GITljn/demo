package com.ljn.demo.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@SpringBootTest
public class KafkaExample {
    @Autowired
    private Producer producer;

//    @Test
    void testKafka() {
        producer.sendMessage("test", "hello world");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Component
class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }
}

@Component
class Consumer {
    @KafkaListener(topics = "test")
    public void handleMessage(ConsumerRecord record) {
        System.out.println(record.value());
    }
}
