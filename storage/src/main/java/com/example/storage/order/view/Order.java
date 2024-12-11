package com.example.storage.order.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {


    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private int count;


    public Order() {
    }
}
