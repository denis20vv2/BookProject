package com.example.books.author.web;

import com.example.books.book.web.BookDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "Данные об авторе")
@Getter
@Setter
public class AuthorRequestDTO {

    @Schema(description = "id автора", example = "1")
    private Long authorId;

    @Schema(description = "Имя автора", example = "Лев Толстой")
    private String authorName;

    @Schema(description = "Список книг автора")
    private List<BookDTO> books;

}