package com.example.authorizationservice.cell.model.textBlock;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Style {

    @NotNull(message = "fontSize is null")
    private String fontSize;

    @NotNull(message = "color is null")
    private String color;

    @NotNull(message = "fontWeight is null")
    private String fontWeight;
}
