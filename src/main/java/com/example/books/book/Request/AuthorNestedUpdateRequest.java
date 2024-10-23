package com.example.books.book.Request;

import com.example.books.author.Request.AuthorRequestUpdate;
import com.example.books.author.web.AuthorDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorNestedUpdateRequest {

    @Schema(description = "Список аворов")
    @NotNull(message = "Author data is missing")
    List<AuthorRequestUpdate> authors;

}
