package com.example.books.authors;

import com.example.books.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRep extends JpaRepository<Author, Long> {
    Author findByAuthorId(Long authorId);

    Author findByAuthorName(String authorName);
}
