package com.example.books.authors;

import com.example.books.books.Book;
import com.example.books.books.BookRep;
import com.example.books.books.BookView;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/author")
public class AuthorController {
    BookRep bookRep;

    private static final Logger logger = LoggerFactory.getLogger(com.example.books.books.BooksController.class);
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
    public AuthorsView updateBook(@PathVariable Long authorId, @RequestBody Author updatedAuthor) {
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


}
