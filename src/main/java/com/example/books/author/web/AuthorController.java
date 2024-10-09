package com.example.books.author.web;

import com.example.books.author.domain.Author;
import com.example.books.author.rep.AuthorRep;
import com.example.books.author.service.AuthorService;
import com.example.books.book.domain.Book;
import com.example.books.book.web.BookController;
import com.example.books.error.Error;
import com.example.books.book.rep.BookRep;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;





@RestController
@RequestMapping("/author")
public class AuthorController {
    BookRep bookRep;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final AuthorService service;
    AuthorRep authorRep;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    public void authorRep(AuthorRep authorRep) {
        this.authorRep = authorRep;
    }
    public void bookRep(BookRep bookRep) {
        this.bookRep = bookRep;
    }

    @GetMapping("/{authorId}")
    @ResponseBody
    public AuthorsView getAuthorById(@PathVariable Long authorId) {
        logger.info("Получен запрос на получение книги с ID: {}", authorId);
        return service.getViewAuthor(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AuthorsView create(@RequestBody Author author) {
        return service.create(author);
    }

    @PutMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public AuthorsView updateAuthor(@PathVariable Long authorId, @RequestBody Author updatedAuthor) {
        Author author = service.getAuthor(authorId);
        author.setAuthorName(updatedAuthor.getAuthorName());
        Set<Book> updatedBooks = updatedAuthor.getBooks();
        if (!updatedBooks.isEmpty()) {
            Set<Book> booksToSet = new HashSet<>();
            for (Book updatedBook : updatedBooks) {
                if (updatedBook.getBookId() != null) {
                    Book existingBook = bookRep.findById(updatedBook.getBookId())
                            .orElseThrow(() -> new EntityNotFoundException("Book with id " + updatedBook.getBookId() + " not found"));

                    existingBook.setBookName(updatedBook.getBookName());
                    booksToSet.add(existingBook);
                } else {
                    throw new IllegalArgumentException("Book ID is required for update");
                }
            }
            author.setBooks(booksToSet);
        }
        return service.update(author);
    }


    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long authorId){
        service.deleteAuthor(authorId);
    }

    @GetMapping("/sortByName")
    @ResponseBody
    public List<AuthorsView> getSortBooks() {
        return service.getFirstTenAuthors();
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
