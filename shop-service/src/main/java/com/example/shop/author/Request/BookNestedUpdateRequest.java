package com.example.shop.author.Request;

import com.example.shop.book.Request.BookRequestUpdate;
import com.example.shop.book.web.BookDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookNestedUpdateRequest {


    @Schema(description = "Книги автора")
    @NotNull(message = "books is null")
    @NotEmpty
    List<BookRequestUpdate> books;

}

