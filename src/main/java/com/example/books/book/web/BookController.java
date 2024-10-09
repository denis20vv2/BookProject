package com.example.books.book.web;

import com.example.books.author.rep.AuthorRep;
import com.example.books.book.service.BookService;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
import com.example.books.error.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.books.author.domain.Author;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name="Book")
public class BookController {
    BookRep bookRep;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService service;
     AuthorRep authorRep;

    public BookController(BookService service) {
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
    @Operation(
            summary = "Получение книги по ID",
            description = "Позволяет зарегистрировать пользователя"
    )
    public BookView getBookById(@PathVariable Long bookId) {
        logger.info("Получен запрос на получение книги с ID: {}", bookId);
        return service.getViewBook(bookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Operation(
            summary = "Создание книги"
    )
    public BookView create(@RequestBody Book book) {
        return service.create(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @Operation(
            summary = "Обновление книги"
    )
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
    @Operation(
            summary = "Удаление книги"
    )
    public void deleteBook(@PathVariable Long bookId){
        service.deleteBook(bookId);
    }

    @GetMapping("/sortByName")
    @ResponseBody
    @Operation(
            summary = "Получение 4х книг с сортировкой по названию в алфаватином порядке"
    )
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



