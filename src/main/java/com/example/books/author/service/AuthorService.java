package com.example.books.author.service;

import com.example.books.author.rep.AuthorRep;
import com.example.books.author.web.*;
import com.example.books.author.converter.AuthorToAuthorViewConverter;
import com.example.books.author.domain.Author;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
import com.example.books.book.service.BookService;
import com.example.books.book.web.BookDTO;
import com.example.books.book.web.BookView;
import com.example.books.book.web.BookViewNested;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final AuthorToAuthorViewConverter authorToAuthorViewConverter;
    private final BookRep bookRep;
    private final AuthorRep authorRep;

    public Author getAuthor(Long authorId) {
        return authorRep.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + authorId + " not found"));
    }

    public AuthorView getViewAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        return authorToAuthorViewConverter.convert(author);
    }


    public AuthorView getAuthorByName(String authorName) {
        Author author = authorRep.findByAuthorName(authorName);
        return authorToAuthorViewConverter.convert(author);
    }

    public AuthorView createAuthor(AuthorViewNested authorRequestDTO) {
        if (authorRequestDTO.getAuthorName() == null) {
            throw new HttpMessageNotReadableException("Author data is missing");
        }

        Author author = new Author();
        //author.setAuthorId(authorRequestDTO.getAuthorId());
        author.setAuthorName(authorRequestDTO.getAuthorName());

        Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(savedAuthor);
    }

    /*public AuthorView createAuthorDependency(AuthorRequestDTO authorRequestDTO) {
        if (authorRequestDTO.getAuthorName() == null) {
            throw new HttpMessageNotReadableException("Author data is missing");
        }

        Author author = new Author();
        //author.setAuthorId(authorRequestDTO.getAuthorId());
        author.setAuthorName(authorRequestDTO.getAuthorName());

        List<BookDTO> bookRequestList = authorRequestDTO.getBooks();
        Set<Book> books = new HashSet<>();

        for (BookDTO bookDTO : bookRequestList) {

            Long bookId = bookDTO.getBookId();

            if (bookId != null) {

                logger.info("Получен запрос на привязку существующей книги книги");

                Book book = bookRep.findById(bookDTO.getBookId())
                        .orElseThrow(() -> new EntityNotFoundException("Book with ID  not found."));

                books.add(book);

            } else {

                logger.info("Получен запрос на создание новой книги");
                Book book = new Book();
                book.setBookName(bookDTO.getBookName());
                books.add(book);

            }

        }

            author.setBooks(books);


        Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(savedAuthor);
    }*/

    public AuthorView updateAuthorName(Long authorId, AuthorNameUpdateRequest authorRequestDTO) {

        if (authorRequestDTO == null || authorRequestDTO.getAuthorName() == null) {
            throw new HttpMessageNotReadableException("Author data is missing");
        }

        Author author = getAuthor(authorId);

        author.setAuthorName(authorRequestDTO.getAuthorName());

        return authorToAuthorViewConverter.convert(authorRep.save(author));
    }

    public AuthorView updateBookNested(AuthorRequestDTO authorRequestDTO) {

        if (authorRequestDTO == null || authorRequestDTO.getBooks().isEmpty()) {
            throw new HttpMessageNotReadableException("Author data is missing");
        }

        Long authorId = authorRequestDTO.getAuthorId();
        Author author = authorRep.findByAuthorId(authorId);

        List<BookDTO> bookRequestList = authorRequestDTO.getBooks();
        Set<Book> books = new HashSet<>();

        for(int numberElementList = 0; numberElementList < bookRequestList.size(); numberElementList++ ) {

            //logger.info("fatal1");
            Long bookId = bookRequestList.get(numberElementList).getBookId();

            logger.info("fatal2");


            if (bookId != null) {

                logger.info("Получен запрос на привязку существующей книги");

                Book book = bookRep.findById((bookRequestList.get(numberElementList)).getBookId())
                        .orElseThrow(() -> new EntityNotFoundException("Book with ID  not found."));

                books.add(book);

            }

            else {

                logger.info("Получен запрос на создание новой книги");
                Book book = new Book();
                logger.info("fatal2");
                book.setBookName((bookRequestList.get(numberElementList)).getBookName());
                logger.info("fatal2");
                books.add(book);

            }
        }

        author.setBooks(books);

        Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(savedAuthor);
    }

    public void deleteAuthor(Long authorId) {
        if (authorRep.findById(authorId).isPresent())  {
            authorRep.deleteById(authorId);
        } else {
            throw new EntityNotFoundException("Author with id not found");
        }
    }

    public Page<AuthorView> getAuthorsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("authorName").ascending());
        return authorRep.findAll(pageable)
                .map(authorToAuthorViewConverter::convert);
    }

    public List<AuthorView> getFirstTenAuthors() {

        Sort sort = Sort.by(Sort.Direction.ASC, "authorName");
        List<Author> sortedAuthors = (List<Author>) authorRep.findAll(sort);
        sortedAuthors = sortedAuthors.stream().limit(4).collect(Collectors.toList());
        List<AuthorView> views = new ArrayList<>();
        sortedAuthors.forEach(author -> views.add(authorToAuthorViewConverter.convert(author)));

        return new ArrayList<>(views);
    }
}
