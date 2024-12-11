package com.example.shop.order.rep;

import com.example.shop.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRep extends JpaRepository<Order, Long> {


    Order getByOrderId(Long orderId);
}
