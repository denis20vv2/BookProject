package com.example.storage.order.kafkaListner;

import com.example.storage.author.rep.AuthorRep;
import com.example.storage.book.domain.Book;
import com.example.storage.book.rep.BookRep;
import com.example.storage.error.InsufficientStockException;
import com.example.storage.order.converter.OrderToOrderResponseConverter;
import com.example.storage.order.view.Order;
import com.example.storage.order.view.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@AllArgsConstructor
@Service
public class OrderService {

    private final BookRep bookRep;
    private final AuthorRep authorRep;

    private final OrderToOrderResponseConverter orderToOrderResponseConverter;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public Book getBook(String bookName) {
        Book book = bookRep.findByBookName(bookName);
        if (book == null) {
            throw new EntityNotFoundException("Book with bookName '" + bookName + "' not found");
        }
        return book;
    }

    public OrderResponse checkBook(Order order){
        logger.info("Getting object(ServiceLog): " + order);
        //Book book = getBook(order.getBookName());

        OrderResponse orderResponse = new OrderResponse();

        /*int existingQuantity = book.getCount();
        int requiredQuantity = order.getCount();*/

        try {

            Book book = getBook(order.getBookName()); // Метод, который может выбросить исключение
            int existingQuantity = book.getCount();
            int requiredQuantity = order.getCount();


            if (requiredQuantity > existingQuantity) {
                logger.info(" There are not enough books ");
                //throw new InsufficientStockException(" На складе недостаточное количесвто книг для заказа \n Требуется: " + requiredQuantity + " Имеется: " + existingQuantity  ,existingQuantity,requiredQuantity );

                orderResponse = orderToOrderResponseConverter.convert(order);
                orderResponse.setMessage("На складе недостаточное количесвто книг для заказа \n Требуется: " + requiredQuantity + " Имеется: " + existingQuantity);
                orderResponse.setStatus("Не успешно");
                return orderResponse;

            } else {
                book.setCount(existingQuantity - requiredQuantity);
                bookRep.save(book);
                logger.info(" request is successful ");

                orderResponse = orderToOrderResponseConverter.convert(order);
                orderResponse.setMessage("Заказ создан");
                orderResponse.setStatus("Успешно");

                return orderResponse;

            }
        }catch(EntityNotFoundException e){

            logger.error("Error while fetching book: " + e.getMessage(), e);
            orderResponse = orderToOrderResponseConverter.convert(order);
            orderResponse.setMessage("Книга не найдена: " + order.getBookName());
            orderResponse.setStatus("Не успешно");
            return orderResponse;

        }


    }

}
