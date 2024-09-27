package com.example.books.authors;

import com.example.books.books.Book;
import com.example.books.books.BookRep;
import com.example.books.books.BookToBookViewConverter;
import com.example.books.books.BookView;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(com.example.books.books.BookService.class);

    private final AuthorToAuthorViewsConverter authorToAuthorViewsConverter;
    private final BookRep bookRep;
    private final AuthorRep authorRep;

    public AuthorService(AuthorToAuthorViewsConverter authorToAuthorViewsConverter, BookRep bookRep, AuthorRep authorRep) {
        this.authorToAuthorViewsConverter = authorToAuthorViewsConverter;
        this.bookRep = bookRep;
        this.authorRep = authorRep;
    }

    public Author getAuthor(Long authorId) {
        return authorRep.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id not found"));
    }

    public AuthorsView getViewAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        return authorToAuthorViewsConverter.convert(author);
    }

    public AuthorsView create(Author author) {

        Set<Book> books = author.getBooks();

        if (books != null && !books.isEmpty()) {
            Set<Book> existingBooks = null;
            for (Book existingBook : books) {

                if (existingBook.getBookId() != null) {
                    Book findBook = bookRep.findById(existingBook.getBookId())
                            .orElseThrow(() -> new EntityNotFoundException("Book with id not found"));

                    findBook.setBookName(existingBook.getBookName());
                    existingBooks.add(findBook);
                } else {
                    throw new IllegalArgumentException("Book ID is required for update");
                }
            }
            author.setBooks(existingBooks);
        }

        return authorToAuthorViewsConverter.convert(authorRep.save(author));
    }

    public AuthorsView update(Author author) {

        Author authorSave = authorRep.save(author);
        return authorToAuthorViewsConverter.convert(authorSave);
    }

    //    @Transactional

    public void deleteAuthor(Long authorId) {
        try {
            authorRep.deleteById(authorId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Author with id not found");
        }
    }

    public List<AuthorsView> getFirstTenAuthors() {

        Sort sort = Sort.by(Sort.Direction.ASC, "authorName");
        List<Author> sortedAuthors = (List<Author>) authorRep.findAll(sort);
        sortedAuthors = sortedAuthors.stream().limit(4).collect(Collectors.toList());
        List<AuthorsView> views = new ArrayList<>();
        sortedAuthors.forEach(author -> views.add(authorToAuthorViewsConverter.convert(author)));

        return new ArrayList<>(views);
    }

}
