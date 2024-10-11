package com.example.books.book.web;

import com.example.books.author.domain.Author;
import com.example.books.author.web.AuthorReqForBook;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
public class BookReq {


    private Long bookId;

    private String bookName;

    private AuthorReqForBook author;


}
