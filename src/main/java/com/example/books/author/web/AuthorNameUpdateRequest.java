package com.example.books.author.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorNameUpdateRequest {

    @Schema(description = "Имя автора", example = "author")
    String authorName;

}
