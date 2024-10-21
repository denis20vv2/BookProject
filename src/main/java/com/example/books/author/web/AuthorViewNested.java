package com.example.books.author.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Данные об авторе")
@Getter
@Setter
public class AuthorViewNested {

    @Schema(description = "Имя автора", example = "author")
    @NotNull(message = "Author data is missing")
    private String authorName;

}
