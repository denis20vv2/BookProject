package com.example.books.book.converter;

import com.example.books.author.domain.Author;
import com.example.books.author.web.AuthorReqForBook;
import com.example.books.book.domain.Book;
import com.example.books.book.web.BookReq;
import com.example.books.book.web.BookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
/*
@Component
public class BookReqToBookConverter implements Converter<BookReq, Book> {

    /*  @Override
    public Book convert(BookReq source) {
        Book book = new Book();
        book.setBookId(BookReq.getBookId());
        book.setBookName(BookReq.getBookName());
        Author author = BookReq.getAuthor();
        return book;
    }


}*/
