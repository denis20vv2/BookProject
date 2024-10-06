package com.example.books;

import com.example.books.books.Book;
import com.example.books.books.BookRep;
import com.example.books.books.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

        @Mock
        private BookRep bookRep;

        @InjectMocks
        private BookService bookService;

        @Test
        void getBook_WhenBookExists() {

            Long bookId = 1L;
            Book expectedBook = new Book();
            expectedBook.setBookId(bookId);
            when(bookRep.findById(bookId)).thenReturn(Optional.of(expectedBook));
            //Book actualBook = bookService.getBook(bookId);


            assertEquals(expectedBook, bookService.getBook(bookId));

        }

        @Test
        void getBook_WhenBookDoesNotExist() {

            Long bookId = 1L;
            when(bookRep.findById(bookId)).thenReturn(Optional.empty());


            EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
                bookService.getBook(bookId);
            });
            assertEquals("Book with id " + bookId + " not found", exception.getMessage());

        }
    }

