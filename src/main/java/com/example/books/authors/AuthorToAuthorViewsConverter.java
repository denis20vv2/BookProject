package com.example.books.authors;

import com.example.books.books.Book;
import com.example.books.books.BookToBooksViewConverter;
import com.example.books.books.BooksView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorToAuthorViewsConverter implements Converter<Author, AuthorsView>  {

    private final BookToBooksViewConverter bookToBookViewConverter;


    public AuthorToAuthorViewsConverter(BookToBooksViewConverter bookToBookViewConverter) {
        this.bookToBookViewConverter = bookToBookViewConverter;
    }


    @Override
    public AuthorsView convert(@NonNull Author author) {
        AuthorsView view = new AuthorsView();
        view.setAuthorName(author.getAuthorName());
        view.setAuthorId(author.getAuthorId());
       // Set<BooksView> book = author.getBooks();

      //  view.setBooks(BookToBooksViewConverter.convert(book));

        Set<BooksView> views = new HashSet<>();
        Set<Book> books= author.getBooks();
        books.forEach(book -> views.add(bookToBookViewConverter.convert(book)));
        view.setBooks(views);

        return view;
    }
}
