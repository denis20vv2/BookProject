package com.example.books.books;

import com.example.books.authors.Author;
import com.example.books.authors.AuthorToAuthorViewConverter;
import com.example.books.authors.AuthorView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.example.books.books.Book;
import com.example.books.books.BookView;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookToBookViewConverter implements Converter<Book, BookView> {

    private final AuthorToAuthorViewConverter authorToAuthorViewConverter;

    public BookToBookViewConverter(AuthorToAuthorViewConverter authorToAuthorViewConverter) {
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
    }



    @Override
    public BookView convert(@NonNull Book book) {

        BookView view = new BookView();
        view.setBookId(book.getBookId());
        view.setBookName(book.getBookName());
        Author author = book.getAuthor();
        view.setAuthors(authorToAuthorViewConverter.convert(author));
        return view;

    }

}
