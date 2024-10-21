package com.example.books.book.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

        @Schema(description = "id книги", example = "1")
        @NotNull(message = "book data is missing")
        private Long bookId;

    }



