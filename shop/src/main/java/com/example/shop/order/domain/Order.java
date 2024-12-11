package com.example.shop.order.domain;

import com.example.shop.order.web.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

        @Id
        @SequenceGenerator(
                name = "order_seq",
                sequenceName = "order_seq",
                allocationSize = 1
        )
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
        @Column(nullable = false)
        private Long orderId;

        @Column(nullable = false)
        private String bookName;

        @Column(nullable = false)
        private int count;

        @Column(nullable = false)
        private String message;

        @Column(nullable = false)
        private String status;



}
