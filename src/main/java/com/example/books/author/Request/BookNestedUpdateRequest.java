package com.example.books.author.Request;

import com.example.books.book.web.BookDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookNestedUpdateRequest {


    @Schema(description = "Книги автора")
    List<BookDTO> books;

}

