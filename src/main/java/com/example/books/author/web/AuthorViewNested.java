package com.example.books.author.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Данные об авторе")
@Getter
@Setter
public class AuthorViewNested {

    @Schema(description = "Имя автора", example = "author")
    private String authorName;

}
