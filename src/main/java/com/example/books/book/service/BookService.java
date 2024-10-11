package com.example.books.book.service;
import com.example.books.author.converter.AuthorToAuthorViewNestedConverter;
import com.example.books.author.domain.Author;
import com.example.books.author.rep.AuthorRep;
import com.example.books.author.web.AuthorReqForBook;
import com.example.books.author.web.AuthorViewNested;
import com.example.books.book.converter.BookToBookViewConverter;
import com.example.books.book.web.BookBaseReq;
import com.example.books.book.web.BookView;
import com.example.books.book.domain.Book;
import com.example.books.book.rep.BookRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final AuthorToAuthorViewNestedConverter authorToAuthorViewNestedConverter;
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

    public BookView create(Book  book) {

        if (book == null)  {
            throw new HttpMessageNotReadableException("Book don t have parametrs");
        }

        Author author = book.getAuthor();

        if (author != null) {
            Author existingAuthor = null;
            logger.info("Author  != null");


            if (author.getAuthorId() == null && author.getAuthorName() != null) {
                existingAuthor = authorRep.findByAuthorName(author.getAuthorName());
                logger.info("Search for the author by name ");
            } else if (author.getAuthorId() != null) {
                existingAuthor = authorRep.findByAuthorId(author.getAuthorId());
                logger.info("Search for the author by id ");
            }

            if (existingAuthor != null) {
                book.setAuthor(existingAuthor);
                logger.info("Found author is installed");
            } else {
                authorRep.save(author);
                logger.info("Author save");
            }
        }

        return bookToBookViewConverter.convert(bookRep.save(book));
    }

    private Book prepare(Book book, BookBaseReq req) {
        book.setBookName(req.getBookName());
        book.setAuthor(req.getAuthor());
        /*List<Player> playerList = playerRepo.findAllById(req.getPlayers()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));*/
        //Set<Player> players = new HashSet<>(playerList);
        //event.setPlayers(players);
        return book;
    }

    public BookView update(Long bookId, Book updatedBook) {


        Book book = getBook(bookId);
        if(updatedBook.getBookName() != null){
            book.setBookName(updatedBook.getBookName());
        }

        Author author = updatedBook.getAuthor();

        if(updatedBook.getBookName() != null && authorRep.findById(author.getAuthorId()) != null){
            book.setAuthor(updatedBook.getAuthor());
        }

        ///////////////////////////////////
        /*book.setBookName(updatedBook.getBookName());

        Author updatedAuthor = updatedBook.getAuthor();

        if (updatedAuthor != null && updatedAuthor.getAuthorId() != null) {
            Author existingAuthor = authorRep.findById(updatedAuthor.getAuthorId())
                    .orElseThrow(() -> new EntityNotFoundException("Book with id  not found"));

            book.setAuthor(existingAuthor);
        }*/

        /////////////////////////////////////

        Book bookSave = bookRep.save(book);
        return bookToBookViewConverter.convert(bookSave);
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



    public List<BookView> getFirstTenBooks() {

        Sort sort = Sort.by(Sort.Direction.ASC, "bookName");
        List<Book> sortedBooks = (List<Book>) bookRep.findAll(sort);
        sortedBooks = sortedBooks.stream().limit(4).collect(Collectors.toList());
        List<BookView> views = new ArrayList<>();
        sortedBooks.forEach(book -> views.add(bookToBookViewConverter.convert(book)));

        return new ArrayList<>(views);
    }

}
