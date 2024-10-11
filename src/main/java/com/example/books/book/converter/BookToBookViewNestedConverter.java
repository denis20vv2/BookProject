package com.example.books.book.converter;

import com.example.books.book.domain.Book;
import com.example.books.book.web.BookViewNested;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
@Component
@NoArgsConstructor
public class BookToBookViewNestedConverter implements Converter<Book, BookViewNested> {

    @Override
    public BookViewNested convert(@NonNull Book book) {
        BookViewNested view = new BookViewNested();
        view.setBookName(book.getBookName());
        return view;
    }

}
