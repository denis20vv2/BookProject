package com.example.books.book.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookNameUpdateRequest {

    @Schema(description = "Название книги", example = "book")
    @NotNull(message = "Book data is missing")
    String bookName;

}
