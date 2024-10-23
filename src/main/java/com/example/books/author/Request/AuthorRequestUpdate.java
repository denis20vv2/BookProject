package com.example.books.author.Request;

import com.example.books.book.web.BookViewNested;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "Данные об авторе")
@Getter
@Setter
public class AuthorRequestUpdate {

    @Schema(description = "id автора", example = "1")
    private Long authorId;

    @Schema(description = "Имя автора", example = "Лев Толстой")
    private String authorName;

}
