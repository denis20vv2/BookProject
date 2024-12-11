package com.example.storage.order.kafkaListner;

import com.example.storage.order.view.Order;
import com.example.storage.order.view.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, OrderResponse> kafkaTemplate;

    public void sendMessage(OrderResponse message, String topicName) {
        log.info("Sending: {}", message);
        log.info("To topic: {}", topicName);

        kafkaTemplate.send(topicName, message);
    }
}
