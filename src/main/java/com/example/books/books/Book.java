package com.example.books.books;

import com.example.books.authors.Author;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    public Book() {

    }

    public Book(String bookName, long bookId) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String bookName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author authors) {
        this.author = authors;
    }
}
