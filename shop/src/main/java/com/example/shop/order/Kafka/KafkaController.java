package com.example.shop.order.Kafka;

import com.example.shop.order.domain.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Author")
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaMessageProducer kafkaMessageProducer;
    private final KafkaService kafkaService;

    public KafkaController(KafkaMessageProducer kafkaMessageProducer, KafkaService kafkaService) {
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.kafkaService = kafkaService;
    }

    @Operation(
            summary = "Запрос на заказ книги",
            description = "Запрос на заказ книги"
    )
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody OrderDTO order) {
        kafkaMessageProducer.sendMessage(order);
        return ResponseEntity.ok("Message sent to Kafka");
    }

    @Operation(
            summary = "Получение данных заказа",
            description = "Получение данных заказа"
    )
    @PostMapping("/get/{orderId}")
    public Order getMessage(@PathVariable long orderId) {

        return kafkaService.getOrder(orderId);
    }

}

