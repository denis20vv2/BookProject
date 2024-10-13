package com.example.books.book.converter;

import com.example.books.author.domain.Author;
import com.example.books.author.converter.AuthorToAuthorViewNestedConverter;
import com.example.books.author.web.AuthorViewNested;
import com.example.books.book.web.BookView;
import com.example.books.book.domain.Book;
import com.example.books.book.web.BookViewNested;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class BookToBookViewConverter implements Converter<Book, BookView> {

    private final AuthorToAuthorViewNestedConverter authorToAuthorViewNestedConverter;

    @Override
    public BookView convert(@NonNull Book book) {

        BookView view = new BookView();
        view.setBookId(book.getBookId());
        view.setBookName(book.getBookName());
        //Author author = book.getAuthors();

        Set<AuthorViewNested> views = new HashSet<>();
        Set<Author> authors= book.getAuthors();

        authors.forEach(author -> views.add(authorToAuthorViewNestedConverter.convert(author)));
        //view.setAuthors(authorToAuthorViewNestedConverter.convert(author));
        view.setAuthors(views);
        return view;

    }

}
