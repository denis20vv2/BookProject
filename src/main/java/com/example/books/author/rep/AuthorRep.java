package com.example.books.author.rep;

import com.example.books.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRep extends JpaRepository<Author, Long> {
    Author findByAuthorId(Long authorId);
    Author findByAuthorName(String authorName);
}
