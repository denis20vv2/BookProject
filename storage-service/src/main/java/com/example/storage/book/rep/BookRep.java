package com.example.storage.book.rep;

import com.example.storage.author.domain.Author;
import com.example.storage.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRep extends JpaRepository<Book, Long> {

    Book findByBookName(String authorName);

    //Book findById(Long bookName);

}
