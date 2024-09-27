package com.example.books.authors;

import com.example.books.books.Book;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    public Author() {

    }


    public Author(String authorName, long authorId) {
            this.authorId = authorId;
            this.authorName = authorName;
        }

    @OneToMany (mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Book> book = new HashSet<>();

        @Id
        @GeneratedValue
        @Column(nullable = false)
        private Long authorId;

        @Column(nullable = false)
        private String authorName;


        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }

        public Long getAuthorId() {
            return authorId;
        }

    public Set<Book> getBooks() {
        return book;
    }

    public void setBooks(Set<Book> books) {
        this.book = books;
    }
}
