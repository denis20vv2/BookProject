package com.example.authorizationservice.cell.model.link;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Styles {

    @NotNull(message = "color is null")
    private String color;

    @NotNull(message = "textDecoration is null")
    private String textDecoration;

    @NotNull(message = "fontSize is null")
    private String fontSize;
}
