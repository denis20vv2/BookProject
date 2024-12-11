package com.example.shop.order.Kafka;

import com.example.shop.order.domain.Order;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void sendMessage(String topicName, OrderDTO message) {
        log.info("Sending: {}", message);
        log.info("To topic: {}", topicName);

        kafkaTemplate.send(topicName, message);
    }
}


