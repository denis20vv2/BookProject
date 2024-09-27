package com.example.books.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRep extends JpaRepository<Book, Long> {

}
