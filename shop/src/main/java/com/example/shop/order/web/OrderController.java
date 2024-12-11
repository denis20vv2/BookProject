package com.example.shop.order.web;

import com.example.shop.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
@Tag(name="Order")
@RequiredArgsConstructor
@Validated
public class OrderController {


    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;




}
