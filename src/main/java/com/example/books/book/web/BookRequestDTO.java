package com.example.books.book.web;

import com.example.books.author.web.AuthorDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "Данные о книге")
@Getter
@Setter
public class BookRequestDTO {

    @Schema(description = "id автора", example = "1")
    @NotNull(message = "Book data is missing")
    private Long BookId;

    /* @Schema(description = "Название книги", example = "book")
    @NotNull(message = "Book data is missing")
   String bookName;*/

    @Schema(description = "Список авторов")
    @NotNull(message = "Book data is missing")
    List<AuthorDTO> authors;

}
