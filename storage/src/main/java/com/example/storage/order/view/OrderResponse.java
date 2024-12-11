package com.example.storage.order.view;

import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse extends Order{

    private String message;
    private String status;

    public OrderResponse(String bookName, int count, String message, String status) {
        super(bookName, count);
        this.message = message;
        this.status = status;
    }

    public OrderResponse() {

    }
}
