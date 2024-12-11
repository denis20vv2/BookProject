package com.example.storage.book.converter;

import com.example.storage.author.domain.Author;
import com.example.storage.author.converter.AuthorToAuthorViewNestedConverter;
import com.example.storage.author.web.AuthorViewNested;
import com.example.storage.book.web.BookView;
import com.example.storage.book.domain.Book;
import com.example.storage.book.web.BookViewNested;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
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
