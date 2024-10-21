package com.example.books.author.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorNameUpdateRequest {

    @Schema(description = "Имя автора", example = "author")
    @NotNull(message = "Author data is missing")
    String authorName;

}
