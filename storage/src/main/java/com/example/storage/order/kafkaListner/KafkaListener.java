package com.example.storage.order.kafkaListner;

import com.example.storage.order.view.Order;
import com.example.storage.order.view.OrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Slf4j
public class KafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListener.class);

    private  KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private  KafkaTemplate <String, OrderResponse> newKafkaTemplate;

    @Autowired
    private  OrderService orderService;


    @Value("${spring.kafka.output-topic}")
    private String outputTopic;


    @org.springframework.kafka.annotation.KafkaListener(
            topics = "order.fct.storage",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listener(String order) {
        logger.info("Message sent: " + order);
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Message sent: " + order);

        try {
            Order orderObject = objectMapper.readValue(order, Order.class);
            logger.info("Message sent: " + order);
            OrderResponse orderResponse = orderService.checkBook(orderObject);
            logger.info("Message sent: " + order);

            newKafkaTemplate.send(outputTopic, orderResponse);


        } catch (Exception e) {
            log.error("Error processing message: {}", order, e);
        }
    }

}
