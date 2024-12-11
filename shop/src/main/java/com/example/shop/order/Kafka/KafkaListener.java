package com.example.shop.order.Kafka;

import com.example.shop.order.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@Slf4j
public class KafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListener.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaService kafkaService;

    public KafkaListener(KafkaTemplate<String, String> kafkaTemplate, KafkaService kafkaService) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaService = kafkaService;
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "order.fct.shop", groupId = "order_group")
    void listener(String order) {
        logger.info("Received message [{}] in group1", order);
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Message to object");
        Order orderObject = null;
        try {
            orderObject = objectMapper.readValue(order, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaService.saveOrder(orderObject);
        logger.info("Object saved");


    }
}
