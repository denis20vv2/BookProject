package com.example.storage.author.converter;

import com.example.storage.author.web.AuthorView;
import com.example.storage.author.domain.Author;
import com.example.storage.book.domain.Book;
import com.example.storage.book.converter.BookToBookViewNestedConverter;
import com.example.storage.book.web.BookViewNested;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorToAuthorViewConverter implements Converter<Author, AuthorView>  {

    private final BookToBookViewNestedConverter bookToBookViewNestedConverter;

    @Override
    public AuthorView convert(@NonNull Author author) {
        Set<BookViewNested> bookViewNesteds = author.getBooks().stream()
                .map(bookToBookViewNestedConverter::convert)
                .collect(Collectors.toSet());

        AuthorView view = new AuthorView();
        view.setAuthorName(author.getAuthorName());
        view.setAuthorId(author.getAuthorId());
       // Set<BooksView> book = author.getBooks();

      //  view.setBooks(BookToBooksViewConverter.convert(book));

//        Set<BookViewNested> views = new HashSet<>();
//        Set<Book> books= author.getBooks();
//        books.forEach(book -> views.add(bookToBookViewNestedConverter.convert(book)));
        view.setBooks(bookViewNesteds);

        return view;
    }
}
