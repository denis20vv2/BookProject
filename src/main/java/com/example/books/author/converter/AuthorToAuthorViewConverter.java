package com.example.books.author.converter;

import com.example.books.author.web.AuthorView;
import com.example.books.author.domain.Author;
import com.example.books.book.domain.Book;
import com.example.books.book.converter.BookToBookViewNestedConverter;
import com.example.books.book.web.BookViewNested;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthorToAuthorViewConverter implements Converter<Author, AuthorView>  {

    private final BookToBookViewNestedConverter bookToBookViewNestedConverter;

    @Override
    public AuthorView convert(@NonNull Author author) {
        AuthorView view = new AuthorView();
        view.setAuthorName(author.getAuthorName());
        view.setAuthorId(author.getAuthorId());
       // Set<BooksView> book = author.getBooks();

      //  view.setBooks(BookToBooksViewConverter.convert(book));

        Set<BookViewNested> views = new HashSet<>();
        Set<Book> books= author.getBooks();
        books.forEach(book -> views.add(bookToBookViewNestedConverter.convert(book)));
        view.setBooks(views);

        return view;
    }
}
