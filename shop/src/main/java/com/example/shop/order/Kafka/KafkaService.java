package com.example.shop.order.Kafka;

import com.example.shop.order.domain.Order;
import com.example.shop.order.rep.OrderRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaService {

    private OrderRep orderRep;

    public Order saveOrder(Order order){

        return orderRep.save(order);
    }

    public Order getOrder(Long orderId){

        Order order = orderRep.getByOrderId(orderId);
        return order;
    }

   /* public Order saveOrder(Order order){


        return orderRep.save(order);
    }*/

}
