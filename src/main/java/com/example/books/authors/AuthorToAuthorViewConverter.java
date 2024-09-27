package com.example.books.authors;

import com.example.books.books.Book;
import com.example.books.authors.Author;
import com.example.books.authors.AuthorView;
import com.example.books.books.BookToBookViewConverter;
import com.example.books.books.BookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorToAuthorViewConverter implements Converter<Author, AuthorView> {



    public AuthorToAuthorViewConverter() {
    }

    @Override
    public AuthorView convert(@NonNull Author author) {
        AuthorView view = new AuthorView();
        view.setAuthorName(author.getAuthorName());
        return view;
    }

}
