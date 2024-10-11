package com.example.books.book.web;

import com.example.books.author.domain.Author;
import com.example.books.author.web.AuthorViewNested;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BookView {
    private Long bookId;
    private String bookName;
    private Set<Author> authors;
}
