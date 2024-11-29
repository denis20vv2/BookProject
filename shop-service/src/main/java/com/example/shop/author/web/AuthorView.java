package com.example.shop.author.web;

import com.example.shop.book.web.BookViewNested;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthorView {

    private String authorName;
    private Long authorId;
    private Set<BookViewNested> books;

}
