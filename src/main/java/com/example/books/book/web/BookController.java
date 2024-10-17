package com.example.books.book.web;

import com.example.books.book.service.BookService;
import com.example.books.book.domain.Book;
import com.example.books.error.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.books.error.ControllerAdvice;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name="Book")
@RequiredArgsConstructor
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the book"),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/id/{bookId}")
    @ResponseBody
    @Operation(
            summary = "Получение книги по ID",
            description = "Позволяет найти книгу по id"
    )
    public BookView getBookById(@PathVariable Long bookId) {
        logger.info("Получен запрос на получение книги с ID: {}", bookId);
        return service.getViewBook(bookId);
    }

    @GetMapping("/Name/{bookName}")
    @ResponseBody
    @Operation(
            summary = "Получение книги по названию",
            description = "Позволяет найти книгу по name"
    )
    public BookView getBookByName(@PathVariable String bookName) {
        logger.info("Получен запрос на получение книги с названием: {}", bookName);
        return service.getViewBookByName(bookName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Operation(
            summary = "Создание книги"
    )
    public BookView create(@RequestBody BookRequestDTO bookRequestDTO) {
        return service.create(bookRequestDTO);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @Operation(
            summary = "Обновление книги"
    )
    public BookView updateBook(@PathVariable Long bookId, @RequestBody BookRequestDTO bookRequestDTO) {
        return service.update(bookId, bookRequestDTO);
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

    @GetMapping("/allBooksPagination")
    @ResponseBody
    @Operation(
            summary = "Получение всех книг с пагинацией"
    )
    public Page<BookView> getBooksWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        return service.getBooksWithPagination(page, size);
    }

}



