package com.example.shop.order.Kafka;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Данные заказа")
@Getter
@Setter
public class OrderDTO {


    @Schema(description = "bookName", example = "book1")
    @Column(nullable = false)
    private String bookName;

    @Schema(description = "count", example = "1")
    @Column(nullable = false)
    private int count;

}
