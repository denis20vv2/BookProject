package com.example.books.book.Request;

import com.example.books.author.web.AuthorViewNested;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "Данные о книге")
@Getter
@Setter
public class BookRequestDTOUpdate {

        @Schema(description = "id книги", example = "1")
        @NotNull(message = "Book data is missing")
        private Long bookId;

    /*@Schema(description = "Имя автора", example = "Лев Толстой")
    @NotNull(message = "Author data is missing")
    private String authorName;*/

        @Schema(description = "Список авторов")
        @NotNull(message = "Authors data is missing")
        private List<AuthorViewNested> authors;
}
