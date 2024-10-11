package com.example.books;

import com.example.books.author.rep.AuthorRep;
import com.example.books.author.converter.AuthorToAuthorViewNestedConverter;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
import com.example.books.book.converter.BookToBookViewConverter;
import com.example.books.book.service.BookService;
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


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

        @Mock
        private AuthorRep authorRep;
        @Mock
        private BookRep bookRep;

        @InjectMocks
        private BookService bookService;

        @Mock
        private BookToBookViewConverter bookToBookViewConverter;

        @Mock
        private AuthorToAuthorViewNestedConverter authorToAuthorViewConverter;

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

    /* @Test
    void createBook_WhenBookIsFull() {


        Long bookId = 1L;
        Book expectedBook = new Book();
        expectedBook.setBookId(bookId);

        when(bookRep.findById(bookId)).thenReturn(Optional.of(expectedBook));

        BookView expectedBookView = new BookView();
        expectedBookView.setBookId(bookId);

        Author author = new Author();
        author.setAuthorId(1L);
        expectedBook.setAuthor(author);

        AuthorView expectedAuthorView = new AuthorView();
        expectedAuthorView.setAuthorName(author.getAuthorName());

        doReturn(expectedAuthorView).when(authorToAuthorViewConverter).convert(author);

        doReturn(expectedBookView).when(bookToBookViewConverter).convert(expectedBook);



        Book book =  new Book();
        book.setBookId(bookId);
        book.setAuthor(author);

        book.setBookId(bookId);
        assertEquals(expectedBookView, bookService.create(book));

    }*/

    }

