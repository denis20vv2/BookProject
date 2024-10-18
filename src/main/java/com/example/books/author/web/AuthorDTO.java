package com.example.books.author.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {

    @Schema(description = "id автора", example = "1")
    private Long authorId;

    @Schema(description = "Имя автора", example = "author")
    String authorName;
}
