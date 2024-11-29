package com.example.shop.book.web;

import com.example.shop.author.domain.Author;
import com.example.shop.author.web.AuthorViewNested;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BookView {
    private Long bookId;
    private String bookName;
    private Set<AuthorViewNested> authors;
}
