package com.example.books.book.web;

import com.example.books.Base.BaseRequest;
import com.example.books.author.domain.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
