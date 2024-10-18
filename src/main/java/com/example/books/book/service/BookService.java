package com.example.books.book.service;
import com.example.books.author.converter.AuthorToAuthorViewNestedConverter;
import com.example.books.author.domain.Author;
import com.example.books.author.rep.AuthorRep;
import com.example.books.author.web.AuthorDTO;
//import com.example.books.author.web.bookRequestDTO;
import com.example.books.author.web.AuthorRequestDTO;
import com.example.books.author.web.AuthorView;
import com.example.books.book.converter.BookToBookViewConverter;
//import com.example.books.book.converter.BookViewToBookConverter;
import com.example.books.book.web.*;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
//import jakarta.persistence.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
//import com.example.books.error.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final AuthorToAuthorViewNestedConverter authorToAuthorViewNestedConverter;
    private final BookToBookViewConverter bookToBookViewConverter;
    //private final BookViewToBookConverter bookViewToBookConverter;
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
        if (bookRequestDTO.getBookName() == null) {
            throw new HttpMessageNotReadableException("Book data is missing");
        }

        Book book = new Book();
        //author.setAuthorId(authorRequestDTO.getAuthorId());
        book.setBookName(bookRequestDTO.getBookName());

        Book savedBook = bookRep.save(book);

        return bookToBookViewConverter.convert(savedBook);
    }


    /*public BookView create(BookRequestDTO bookRequestDTO) {
        if (bookRequestDTO.getBookName() == null || bookRequestDTO.getAuthors().isEmpty() || bookRequestDTO == null ) {
            throw new HttpMessageNotReadableException("Book data is missing");
        }

        Book book = new Book();
        //author.setAuthorId(authorRequestDTO.getAuthorId());
        book.setBookName(bookRequestDTO.getBookName());

        List<AuthorDTO> authorRequestList = bookRequestDTO.getAuthors();
        Set<Author> authors = new HashSet<>();

        for(int numberElementList = 0; numberElementList < authorRequestList.size(); numberElementList++ ) {

            Long authorId = authorRequestList.get(numberElementList).getAuthorId();

            if (authorId != null) {

                logger.info("Получен запрос на привязку существующего автора");

                Author author = authorRep.findById((authorRequestList.get(numberElementList)).getAuthorId())
                        .orElseThrow(() -> new EntityNotFoundException("Author with ID  not found."));

                authors.add(author);

            }

            else {

                logger.info("Получен запрос на создание нового автора и првязку");
                Author author = new Author();
                author.setAuthorName((authorRequestList.get(numberElementList)).getAuthorName());
                authors.add(author);

            }

        }

        book.setAuthors(authors);


        Book savedBook = bookRep.save(book);

        return bookToBookViewConverter.convert(savedBook);
    }*/

    public BookView updateNameBook(Long bookId, BookNameUpdateRequest bookRequestDTO) {


        if (bookRequestDTO == null) {
            throw new HttpMessageNotReadableException("Book data is missing");
        }

        Book book = getBook(bookId);

        book.setBookName(bookRequestDTO.getBookName());

        //Book savedBook = bookRep.save(book);
        return bookToBookViewConverter.convert(bookRep.save(book));
    }

    public BookView updateAuthorNested(BookRequestDTO bookRequestDTO) {

        if (bookRequestDTO == null ) {
            throw new HttpMessageNotReadableException("Book data is missing");
        }
        Long bookId = bookRequestDTO.getBookId();
        Book book = getBook(bookId);

        List<AuthorDTO> authorRequestList = bookRequestDTO.getAuthors();
        Set<Author> authors = new HashSet<>();

        for(int numberElementList = 0; numberElementList < authorRequestList.size(); numberElementList++ ) {

            Long authorId = authorRequestList.get(numberElementList).getAuthorId();

            if (authorId != null) {

                logger.info("Получен запрос на привязку существующего автора");

                Author author = authorRep.findById((authorRequestList.get(numberElementList)).getAuthorId())
                        .orElseThrow(() -> new EntityNotFoundException("Author with ID  not found."));

                authors.add(author);

            }

            else {

                logger.info("Получен запрос на создание нового автора и првязку");
                Author author = new Author();
                author.setAuthorName((authorRequestList.get(numberElementList)).getAuthorName());
                authors.add(author);

            }

        }

        book.setAuthors(authors);

        Book savedBook = bookRep.save(book);

        return bookToBookViewConverter.convert(savedBook);
    }

    //    @Transactional

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
        sortedBooks = sortedBooks.stream().limit(4).collect(Collectors.toList());
        List<BookView> views = new ArrayList<>();
        sortedBooks.forEach(book -> views.add(bookToBookViewConverter.convert(book)));

        return new ArrayList<>(views);
    }

}
