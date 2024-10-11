package com.example.books.book.converter;

import com.example.books.author.domain.Author;
import com.example.books.author.converter.AuthorToAuthorViewNestedConverter;
import com.example.books.book.web.BookView;
import com.example.books.book.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookToBookViewConverter implements Converter<Book, BookView> {

    private final AuthorToAuthorViewNestedConverter authorToAuthorViewNestedConverter;

    @Override
    public BookView convert(@NonNull Book book) {

        BookView view = new BookView();
        view.setBookId(book.getBookId());
        view.setBookName(book.getBookName());
        Author author = book.getAuthor();
        view.setAuthors(authorToAuthorViewNestedConverter.convert(author));
        return view;

    }

}
