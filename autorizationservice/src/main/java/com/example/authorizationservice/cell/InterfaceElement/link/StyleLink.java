package com.example.authorizationservice.cell.InterfaceElement.link;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleLink {

    @NotNull(message = "color is null")
    private String color;

    @NotNull(message = "textDecoration is null")
    private String textDecoration;

    @NotNull(message = "fontSize is null")
    private String fontSize;

    @Override
    public String toString() {
        return "StylesLink{" +
                "color='" + color + '\'' +
                ", textDecoration='" + textDecoration + '\'' +
                ", fontSize='" + fontSize + '\'' +
                '}';
    }

}
