package com.example.books.author.service;

import com.example.books.author.rep.AuthorRep;
import com.example.books.author.web.AuthorView;
import com.example.books.author.converter.AuthorToAuthorViewConverter;
import com.example.books.author.domain.Author;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
import com.example.books.book.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final AuthorToAuthorViewConverter authorToAuthorViewsConverter;
    private final BookRep bookRep;
    private final AuthorRep authorRep;

    public Author getAuthor(Long authorId) {
        return authorRep.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + authorId + " not found"));
    }

    public AuthorView getViewAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        return authorToAuthorViewsConverter.convert(author);
    }

    public AuthorView create(Author author) {

        if (author == null)  {
            throw new HttpMessageNotReadableException("Author don t have parameters");
        }
        else {

            Set<Book> books = author.getBooks();

            if (books != null && !books.isEmpty()) {
                Set<Book> existingBooks = null;
                for (Book existingBook : books) {

                    if (existingBook.getBookId() != null) {
                        Book findBook = bookRep.findById(existingBook.getBookId())
                                .orElseThrow(() -> new EntityNotFoundException("Book with id not found"));

                        findBook.setBookName(existingBook.getBookName());
                        existingBooks.add(findBook);
                    } else {
                        throw new EntityNotFoundException("Book ID is required for update");
                    }
                }
                author.setBooks(existingBooks);
            }
        }

        return authorToAuthorViewsConverter.convert(authorRep.save(author));
    }

    public AuthorView update(Long authorId, Author updatedAuthor) {

        ///////////////////////////////////////////////////

        Author author = getAuthor(authorId);
        if(updatedAuthor.getAuthorName() != null){
        author.setAuthorName(updatedAuthor.getAuthorName());
        }

        Set<Book> updatedBooks = updatedAuthor.getBooks();

        /*if(updatedBooks != null){


        }


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
        }*/

        ////////////////////////////////////////////////////


        Author authorSave = authorRep.save(author);
        return authorToAuthorViewsConverter.convert(authorSave);
    }

    //    @Transactional

    public void deleteAuthor(Long authorId) {
        if (authorRep.findById(authorId).isPresent())  {
            authorRep.deleteById(authorId);
        } else {
            throw new EntityNotFoundException("Author with id not found");
        }
    }

    public List<AuthorView> getFirstTenAuthors() {

        Sort sort = Sort.by(Sort.Direction.ASC, "authorName");
        List<Author> sortedAuthors = (List<Author>) authorRep.findAll(sort);
        sortedAuthors = sortedAuthors.stream().limit(4).collect(Collectors.toList());
        List<AuthorView> views = new ArrayList<>();
        sortedAuthors.forEach(author -> views.add(authorToAuthorViewsConverter.convert(author)));

        return new ArrayList<>(views);



       // return new ArrayList<>(views);
    }

}
