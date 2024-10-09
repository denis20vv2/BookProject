package com.example.books.author.web;

import com.example.books.book.web.BooksView;

import java.util.Set;

public class AuthorsView {

    private String authorName;
    private Long authorId;

    private Set<BooksView> books;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }




    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Set<BooksView> getBooks() {
        return books;
    }

    public void setBooks(Set<BooksView> books) {
        this.books = books;
    }
}
