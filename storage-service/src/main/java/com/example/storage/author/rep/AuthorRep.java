package com.example.storage.author.rep;

import com.example.storage.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRep extends JpaRepository<Author, Long> {
    Author findByAuthorId(Long authorId);
    //Author findByAuthorName(String authorName);


    Author findByAuthorName(String authorName);
}
