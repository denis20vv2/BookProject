package com.example.storage.book.Request;

import com.example.storage.author.Request.AuthorRequestUpdate;
import com.example.storage.author.web.AuthorDTO;
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
