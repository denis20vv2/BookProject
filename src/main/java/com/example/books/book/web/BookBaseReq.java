package com.example.books.book.web;

import com.example.books.author.domain.Author;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBaseReq {

        @NotNull
        private Long bookId;
        @NotNull
        private String bookName;
        @NotNull
        private Author author;
}
