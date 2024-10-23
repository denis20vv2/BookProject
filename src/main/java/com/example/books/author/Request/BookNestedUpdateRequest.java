package com.example.books.author.Request;

import com.example.books.book.Request.BookRequestUpdate;
import com.example.books.book.web.BookDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookNestedUpdateRequest {


    @Schema(description = "Книги автора")
    @NotNull(message = "books is null")
    List<BookRequestUpdate> books;

}

