package com.example.books.book.service;
import com.example.books.author.Request.AuthorRequestUpdate;
import com.example.books.author.domain.Author;
import com.example.books.author.rep.AuthorRep;
import com.example.books.author.web.*;
import com.example.books.book.Request.AuthorNestedUpdateRequest;
import com.example.books.book.Request.BookRequestDTO;
import com.example.books.book.Request.BookRequestDTOUpdate;
import com.example.books.book.converter.BookToBookViewConverter;
import com.example.books.book.web.*;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
@AllArgsConstructor
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookToBookViewConverter bookToBookViewConverter;
    private final BookRep bookRep;
    private final AuthorRep authorRep;

    public Book getBook(Long id) {
        return bookRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
    }

    public BookView getViewBook(Long id) {
        Book book = getBook(id);
        return bookToBookViewConverter.convert(book);
    }

    public BookView getViewBookByName(String bookName) {
        Book book = bookRep.findByBookName(bookName);
        if (book == null) {
            throw new EntityNotFoundException("Book with name '" + bookName + "' not found");
        }
        return bookToBookViewConverter.convert(book);
    }

    public BookView create(BookViewNested bookRequestDTO) {

        Book book = new Book();
        book.setBookName(bookRequestDTO.getBookName());

        Book savedBook = bookRep.save(book);

        return bookToBookViewConverter.convert(savedBook);
    }

    public BookView updateBookName(Long bookId, BookViewNested bookRequestDTO) {

        Book book = getBook(bookId);

        book.setBookName(bookRequestDTO.getBookName());
        return bookToBookViewConverter.convert(bookRep.save(book));
    }

    public BookView updateListAuthor(Long bookId, AuthorNestedUpdateRequest bookRequestDTO) {

        if(bookRequestDTO.getAuthors().isEmpty()) {
            throw new IllegalArgumentException("Authors = 0");
        }

        Book book = getBook(bookId);

        List<AuthorRequestUpdate> authorRequestList = bookRequestDTO.getAuthors();
        Set<Author> authors = new HashSet<>();

        for (AuthorRequestUpdate authorRequestUpdate : authorRequestList) {

            Long authorId = authorRequestUpdate.getAuthorId();

            if(authorRequestUpdate.getAuthorId() == null && authorRequestUpdate.getAuthorName() == null) {
                throw new IllegalArgumentException("all attributes of the author object is null");
            }

            if(authorRequestUpdate.getAuthorId() != null && authorRequestUpdate.getAuthorName() != null) {
                throw new IllegalArgumentException("all attributes of the author object is not null");
            }

            if (authorId != null) {

                logger.info("Получен запрос на привязку существующего автора");

                Author author = authorRep.findById(authorRequestUpdate.getAuthorId())
                        .orElseThrow(() -> new EntityNotFoundException("Author with ID  not found."));

                authors.add(author);

            } else {

                logger.info("Получен запрос на создание нового автора и првязку");
                Author author = new Author();
                author.setAuthorName(authorRequestUpdate.getAuthorName());
                authorRep.save(author);
                authors.add(author);

            }

        }

        book.setAuthors(authors);

        Book savedBook = bookRep.save(book);

        return bookToBookViewConverter.convert(savedBook);
    }

    public BookView assignExistingAuthor(BookRequestDTO bookRequestDTO){

        Long bookId = bookRequestDTO.getBookId();
        Book book = getBook(bookId);

        List<AuthorDTO> authorRequestList = bookRequestDTO.getAuthors();
        Set<Author> authors = book.getAuthors();
        if(authorRequestList.isEmpty()) {
            throw new IllegalArgumentException("Authors = 0");
        }

        for (AuthorDTO authorDTO : authorRequestList) {

            if(authorDTO.getAuthorId() == null) {
                throw new IllegalArgumentException("AuthorId is null");
            }

            logger.info("Получен запрос на привязку существующего автора");

            Author author = authorRep.findById(authorDTO.getAuthorId())
                    .orElseThrow(() -> new EntityNotFoundException("Author with ID  not found."));

            authors.add(author);

        }

        book.setAuthors(authors);

        Book savedBook = bookRep.save(book);

        return bookToBookViewConverter.convert(savedBook);
    }

    public BookView assignNewAuthor(BookRequestDTOUpdate bookRequestDTO) {

        Long bookId = bookRequestDTO.getBookId();
        Book book = getBook(bookId);

        List<AuthorViewNested> authorRequestList = bookRequestDTO.getAuthors();
        Set<Author> authors = book.getAuthors();
        if(authorRequestList.isEmpty()) {
            throw new IllegalArgumentException("Authors = 0");
        }

        for (AuthorViewNested authorDTO : authorRequestList) {

            if(authorDTO.getAuthorName() == null) {
                throw new IllegalArgumentException("AuthorName is null");
            }

                logger.info("Получен запрос на создание нового автора и првязку");
                Author author = new Author();
                author.setAuthorName(authorDTO.getAuthorName());
                authorRep.save(author);
                authors.add(author);

        }

        book.setAuthors(authors);

        Book savedBook = bookRep.save(book);

        return bookToBookViewConverter.convert(savedBook);
    }

    public void deleteBook(Long bookId) {
        if (bookRep.findById(bookId).isPresent())  {
            bookRep.deleteById(bookId);
        }
        else {
            throw new EntityNotFoundException("Book with id not found");
        }
    }

    public Page<BookView> getBooksWithPagination( int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookName").ascending());
        return bookRep.findAll(pageable)
                .map(bookToBookViewConverter::convert);
    }

    public List<BookView> getFirstTenBooks() {

        Sort sort = Sort.by(Sort.Direction.ASC, "bookName");
        List<Book> sortedBooks = (List<Book>) bookRep.findAll(sort);
        sortedBooks = sortedBooks.stream().limit(4).toList();
        List<BookView> views = new ArrayList<>();
        sortedBooks.forEach(book -> views.add(bookToBookViewConverter.convert(book)));

        return new ArrayList<>(views);
    }

}
