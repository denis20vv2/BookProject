package com.example.books.book.rep;

import com.example.books.author.domain.Author;
import com.example.books.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRep extends JpaRepository<Book, Long> {

    Book findByBookName(String authorName);

    //Book findById(Long bookName);

}
