package com.example.shop.order.Kafka;

import com.example.shop.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageProducer.class);


    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    @Autowired
    public KafkaMessageProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.topic-name}")
    private String topicName;


    public void sendMessage(OrderDTO order) {
        kafkaTemplate.send(topicName, order);
        logger.info("Message sent: " + String.valueOf(order));
        //System.out.println("Message sent: " + message.toString());
    }
}

