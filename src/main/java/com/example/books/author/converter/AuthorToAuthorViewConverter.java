package com.example.books.author.converter;

import com.example.books.author.web.AuthorView;
import com.example.books.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AuthorToAuthorViewConverter implements Converter<Author, AuthorView> {





    @Override
    public AuthorView convert(@NonNull Author author) {
        AuthorView view = new AuthorView();
        view.setAuthorName(author.getAuthorName());
        return view;
    }

}
