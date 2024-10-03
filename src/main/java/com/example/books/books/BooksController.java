package com.example.books.books;

import com.example.books.authors.AuthorRep;
import com.example.books.error.Error;
import com.example.books.error.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.books.authors.Author;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BooksController {
    BookRep bookRep;

    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);
    private final BookService service;
     AuthorRep authorRep;

    public BooksController(BookService service) {
        this.service = service;
    }

    public void authorRep(AuthorRep authorRep) {
        this.authorRep = authorRep;
    }
    public void bookRep(BookRep bookRep) {
        this.bookRep = bookRep;
    }

    @GetMapping("/{bookId}")
    @ResponseBody
    public BookView getBookById(@PathVariable Long bookId) {
        logger.info("Получен запрос на получение книги с ID: {}", bookId);
        return service.getViewBook(bookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BookView create(@RequestBody Book book) {
        return service.create(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public BookView updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        Book book = service.getBook(bookId);
        book.setBookName(updatedBook.getBookName());

        Author updatedAuthor = updatedBook.getAuthor();

        if (updatedAuthor != null && updatedAuthor.getAuthorId() != null) {
            Author existingAuthor = authorRep.findById(updatedAuthor.getAuthorId())
                    .orElseThrow(() -> new EntityNotFoundException("Book with id  not found"));

            book.setAuthor(existingAuthor);
        }
        return service.update(book);
    }


    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long bookId){
        service.deleteBook(bookId);
    }

    @GetMapping("/sortByName")
    @ResponseBody
    public List<BookView> getSortBooks() {
        return service.getFirstTenBooks();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error(exception.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleExceptionNotParametersException(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new Error(exception.getMessage()));
    }

}



