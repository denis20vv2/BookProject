package com.example.shop.order.web;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Book {
    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String bookName;


}
