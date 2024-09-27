package com.example.books.books;

import com.example.books.authors.Author;
import com.example.books.authors.AuthorView;
import com.example.books.books.BooksView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
@Component
public class BookToBooksViewConverter implements Converter<Book, BooksView> {

    public BookToBooksViewConverter()  {
    }

    @Override
    public BooksView convert(@NonNull Book book) {
        BooksView view = new BooksView();
        view.setBookName(book.getBookName());
        return view;
    }

}
