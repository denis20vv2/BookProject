package com.example.books.book.rep;

import com.example.books.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRep extends JpaRepository<Book, Long> {

}
