package com.example.books.author.web;

import com.example.books.author.domain.Author;
import com.example.books.author.rep.AuthorRep;
import com.example.books.author.service.AuthorService;
import com.example.books.book.web.BookController;
import com.example.books.book.web.BookView;
import com.example.books.error.Error;
import com.example.books.book.rep.BookRep;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Tag(name="Author")
@RequiredArgsConstructor
public class AuthorController {
    BookRep bookRep;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final AuthorService service;

    @GetMapping("/id/{authorId}")
    @ResponseBody
    @Operation(
            summary = "Получение автора по ID",
            description = "Позволяет Получить автора по ID"
    )
    public AuthorView getAuthorById(@PathVariable Long authorId) {
        logger.info("Получен запрос на получение книги с ID: {}", authorId);
        return service.getViewAuthor(authorId);
    }


    @GetMapping("/Name/{authorName}")
    @ResponseBody
    @Operation(
            summary = "Получение книги по названию",
            description = "Позволяет найти книгу по name"
    )
    public AuthorView getAuthorByName(@PathVariable String authorName) {
        logger.info("Получен запрос на получение книги с названием: {}", authorName);
        return service.getAuthorByName(authorName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Operation(
            summary = "Создание нового автора",
            description = "Позволяет создать нового автора в БД"
    )
    public AuthorView create(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return service.create(authorRequestDTO);
    }

    @PutMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @Operation(
            summary = "Обновление автора"
    )
    public AuthorView updateAuthor(@PathVariable Long authorId, @RequestBody AuthorRequestDTO authorRequestDTO) {
        return service.update(authorId, authorRequestDTO);
    }


    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Удаление автора"
    )
    public void deleteAuthor(@PathVariable Long authorId){
        service.deleteAuthor(authorId);
    }

    @GetMapping("/sortByName")
    @ResponseBody
    @Operation(
            summary = "Получение 4х авторов с сортировкой по алфавиту"
    )
    public List<AuthorView> getSortBooks() {
        return service.getFirstTenAuthors();
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error(exception.getMessage()));
    }

    @GetMapping("/allAuthorsPagination")
    @ResponseBody
    @Operation(
            summary = "Получение всех авторов с пагинацией"
    )
    public Page<AuthorView> getAuthorsWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        return service.getAuthorsWithPagination(page, size);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleExceptionNotParametersException(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new Error(exception.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Error> handleExceptionNotParametersException(IllegalStateException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new Error(exception.getMessage()));
    }

}
