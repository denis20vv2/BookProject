package com.example.books.book.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookNameUpdateRequest {

    @Schema(description = "Название книги", example = "book")
    String bookName;

}
