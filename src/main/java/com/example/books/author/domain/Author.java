package com.example.books.author.domain;

import com.example.books.book.domain.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

        @Id
        @GenericGenerator(
                name = "author_seq",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "author_seq"),
                        @org.hibernate.annotations.Parameter(name= "increment_size", value = "1"),
                        @org.hibernate.annotations.Parameter(name= "initial_value", value = "1")
                }
        )
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
        @Column(nullable = false)
        private Long authorId;

        @Column(nullable = false)
        private String authorName;



}
