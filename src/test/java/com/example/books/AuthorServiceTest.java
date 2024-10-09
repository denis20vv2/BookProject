package com.example.books;

import com.example.books.author.domain.Author;
import com.example.books.author.rep.AuthorRep;

import com.example.books.author.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRep authorRep;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void getAuthor_WhenAuthorExists() {

        Long authorId = 1L;
        Author author = new Author();
        author.setAuthorId(authorId);
        when(authorRep.findById(authorId)).thenReturn(Optional.of(author));

        assertEquals(author, authorService.getAuthor(authorId));
    }

    @Test
    void getAuthor_WhenAuthorDoesNotExist() {

        Long authorId = 1L;
        when(authorRep.findById(authorId)).thenReturn(Optional.empty());


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            authorService.getAuthor(authorId);
        });
        assertEquals("Author with id " + authorId + " not found", exception.getMessage());

    }
}

