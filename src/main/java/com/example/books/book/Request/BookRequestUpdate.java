package com.example.books.book.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Данные о книге")
@Getter
@Setter
public class BookRequestUpdate {

    @Schema(description = "id автора", example = "1")
    private Long bookId;

    @Schema(description = "Имя автора", example = "Лев Толстой")
    private String bookName;

}
