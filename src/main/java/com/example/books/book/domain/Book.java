package com.example.books.book.domain;

import com.example.books.author.domain.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GenericGenerator(
            name = "book_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "book_seq"),
                    @org.hibernate.annotations.Parameter(name= "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name= "initial_value", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String bookName;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();

}
