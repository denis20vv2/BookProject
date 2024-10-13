package com.example.books.author.service;

import com.example.books.author.rep.AuthorRep;
import com.example.books.author.web.AuthorRequestDTO;
import com.example.books.author.web.AuthorView;
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

    public AuthorView create(AuthorRequestDTO authorRequestDTO) {
        if (authorRequestDTO == null) {
            throw new HttpMessageNotReadableException("Author data is missing");
        }

        Author author;
        if (authorRequestDTO.getAuthorName() != null) {
            Author existingAuthor = authorRep.findByAuthorName(authorRequestDTO.getAuthorName());
            if (existingAuthor == null) {
                throw new IllegalStateException("Author with name " + authorRequestDTO.getAuthorName() + " already exists");
            } else {
                author = new Author();
            }
        } else {
            author = new Author();
        }

        //author.setAuthorId(authorRequestDTO.getAuthorId());
        author.setAuthorName(authorRequestDTO.getAuthorName());

        List<BookDTO> bookRequestList = authorRequestDTO.getBooks();
        if (bookRequestList != null && !bookRequestList.isEmpty()) {
            Set<Book> books = new HashSet<>();

            for (BookDTO bookDTO : bookRequestList) {
                Book book = new Book();
                book.setBookName(bookDTO.getBookName());
                books.add(book);
            }

            author.setBooks(books);
        }

        Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(savedAuthor);
    }

    public AuthorView update(Long authorId, AuthorRequestDTO authorRequestDTO) {

        Author author = getAuthor(authorId);

        if (authorRequestDTO == null) {
            throw new HttpMessageNotReadableException("Author data is missing");
        }

        if (authorRequestDTO.getAuthorName() != null) {
            author.setAuthorName(authorRequestDTO.getAuthorName());
        }

        List<BookDTO> bookRequestList = authorRequestDTO.getBooks();
        if (bookRequestList != null && !bookRequestList.isEmpty()) {
            Set<Book> books = new HashSet<>();

            for (BookDTO bookDTO : bookRequestList) {
                Book book = new Book();
                book.setBookName(bookDTO.getBookName());
                books.add(book);
            }

            author.setBooks(books);
        }

        //Author savedAuthor = authorRep.save(author);

        return authorToAuthorViewConverter.convert(authorRep.save(author));
    }

    //    @Transactional

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



       // return new ArrayList<>(views);
    }

}
