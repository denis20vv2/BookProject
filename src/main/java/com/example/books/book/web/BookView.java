package com.example.books.book.web;

import com.example.books.author.web.AuthorView;

public class BookView {
    private Long bookId;
    private String bookName;

    private AuthorView authors;
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookId(Long bookId) {this.bookId = bookId;}

    public Long getBookId() {return bookId;}

    public AuthorView getAuthors() {return authors;}
    public void setAuthors(AuthorView authors) {this.authors = authors;}
}
