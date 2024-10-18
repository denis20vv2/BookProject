package com.example.books.book.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Данные о книге")
@Getter
@Setter
public class BookViewNested {

    private String bookName;

}
