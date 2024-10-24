package com.example.books.author.service;

import com.example.books.author.Request.AuthorRequestDTO;
import com.example.books.author.Request.AuthorRequestDtoUpdate;
import com.example.books.author.Request.AuthorRequestUpdate;
import com.example.books.author.Request.BookNestedUpdateRequest;
import com.example.books.author.rep.AuthorRep;
import com.example.books.author.web.*;
import com.example.books.author.converter.AuthorToAuthorViewConverter;
import com.example.books.author.domain.Author;
import com.example.books.book.Request.BookRequestUpdate;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
import com.example.books.book.service.BookService;
import com.example.books.book.web.BookDTO;
import com.example.books.book.web.BookViewNested;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

        Author author = new Author();
        author.setAuthorName(authorRequestDTO.getAuthorName());
        Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(savedAuthor);
    }

    public AuthorView updateAuthorName(Long authorId, AuthorViewNested authorRequestDTO) {

        Author author = getAuthor(authorId);

        author.setAuthorName(authorRequestDTO.getAuthorName());

        return authorToAuthorViewConverter.convert(authorRep.save(author));
    }

    public AuthorView updateListBook(Long authorId, BookNestedUpdateRequest authorRequestDTO) {

        if(authorRequestDTO.getBooks().isEmpty()) {
            throw new IllegalArgumentException("Books = 0.");
        }

        Author author = getAuthor(authorId);

        List<BookRequestUpdate> bookRequestList = authorRequestDTO.getBooks();
        Set<Book> books = new HashSet<>();

        for (BookRequestUpdate bookRequestUpdate : bookRequestList) {

            //logger.info("fatal1");
            Long bookId = bookRequestUpdate.getBookId();

            if(bookRequestUpdate.getBookId() == null && bookRequestUpdate.getBookName() == null) {
                throw new IllegalArgumentException("all attributes of the book object is null");
            }

            //Нужно ли разделить на 2 метода? Сейчас обрабатываем в зависимости от того атрибута, который получаем
            // если bookName заполнен, то берем его и создаем новую книгу
            // если bookId заполнен ищем её в базе, если есть то свяязываем с существующей
            if(bookRequestUpdate.getBookId() != null && bookRequestUpdate.getBookName() != null) {
                throw new IllegalArgumentException("all attributes of the book object is null");
            }


            if (bookId != null) {

                logger.info("Получен запрос на привязку существующей книги");

                Book book = bookRep.findById(bookRequestUpdate.getBookId())
                        .orElseThrow(() -> new EntityNotFoundException("Book with ID  not found."));

                books.add(book);

            } else {

                //info("Получен запрос на создание новой книги");
                Book book = new Book();
                //info("fatal2");
                book.setBookName(bookRequestUpdate.getBookName());
                bookRep.save(book);
                //logger.info("fatal2");
                books.add(book);

            }
        }

        author.setBooks(books);

        Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(savedAuthor);
    }

    public AuthorView assignExistingBook(AuthorRequestDTO authorRequestDTO) {

        Long authorId = authorRequestDTO.getAuthorId();
        Author author = getAuthor(authorId);

        List<BookDTO> bookRequestList = authorRequestDTO.getBooks();
        Set<Book> books = author.getBooks();

        if(bookRequestList.isEmpty()) {
            throw new IllegalArgumentException("Books = 0.");
        }

        for (BookDTO bookDTO : bookRequestList) {

            if(bookDTO.getBookId() == null) {
                throw new IllegalArgumentException("BookId is null.");
            }


            logger.info("Получен запрос на привязку существующей книги");

            Book book = bookRep.findById(bookDTO.getBookId())
                    .orElseThrow(() -> new EntityNotFoundException("Book with ID  not found."));

            books.add(book);

        }

        author.setBooks(books);

        Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(savedAuthor);
    }

    public AuthorView assignNewBook(AuthorRequestDtoUpdate authorRequestDTO) {

        Long authorId = authorRequestDTO.getAuthorId();
        Author author = getAuthor(authorId);


        List<BookViewNested> bookRequestList = authorRequestDTO.getBooks();
        Set<Book> books = author.getBooks();


        if(bookRequestList.isEmpty()) {
            throw new IllegalArgumentException ("Books = 0");
        }

        for (BookViewNested bookViewNested : bookRequestList) {

            if(bookViewNested.getBookName() == null) {
                throw new IllegalArgumentException("BookName is null");
            }

            logger.info("Получен запрос на создание новой книги и првязку");
            Book book = new Book();
            book.setBookName(bookViewNested.getBookName());
            bookRep.save(book);
            books.add(book);

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
        sortedAuthors = sortedAuthors.stream().limit(4).toList();
        List<AuthorView> views = new ArrayList<>();
        sortedAuthors.forEach(author -> views.add(authorToAuthorViewConverter.convert(author)));

        return new ArrayList<>(views);
    }
}
