package com.example.books.book.web;

import com.example.books.author.web.AuthorViewNested;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookView {
    private Long bookId;
    private String bookName;
    private AuthorViewNested authors;
}
