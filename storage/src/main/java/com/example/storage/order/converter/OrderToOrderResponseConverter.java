package com.example.storage.order.converter;

import com.example.storage.author.converter.AuthorToAuthorViewNestedConverter;
import com.example.storage.author.domain.Author;
import com.example.storage.author.web.AuthorViewNested;
import com.example.storage.book.domain.Book;
import com.example.storage.book.web.BookView;
import com.example.storage.order.view.Order;
import com.example.storage.order.view.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
@AllArgsConstructor
public class OrderToOrderResponseConverter implements Converter<Order, OrderResponse> {


        @Override
        public OrderResponse convert(@NonNull Order order) {

            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setBookName(order.getBookName());
            orderResponse.setCount(order.getCount());
            //orderResponse.setUserId(order.getUserId());

            return orderResponse;

        }

    }
