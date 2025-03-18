package com.example.authorizationservice.cell.InterfaceElement.radioGroup;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleRadio {

    @NotNull(message = "direction is null")
    private String direction;

    @NotNull(message = "color is null")
    private String color;

    @NotNull(message = "size is null")
    private String size;

    @Override
    public String toString() {
        return "StyleRadio{" +
                "direction='" + direction + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

}
