package com.example.books.author.converter;

import com.example.books.author.web.AuthorViewNested;
import com.example.books.author.domain.Author;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AuthorToAuthorViewNestedConverter implements Converter<Author, AuthorViewNested> {

    @Override
    public AuthorViewNested convert(@NonNull Author author) {
        AuthorViewNested view = new AuthorViewNested();
        view.setAuthorName(author.getAuthorName());
        return view;
    }

}
