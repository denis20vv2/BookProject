package com.example.books.book.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Данные о книге")
@Getter
@Setter
public class BookViewNested {

    @NotNull(message = "Book data is missing")
    private String bookName;

}
