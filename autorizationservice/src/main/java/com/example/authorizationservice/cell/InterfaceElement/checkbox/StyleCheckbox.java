package com.example.authorizationservice.cell.InterfaceElement.checkbox;

import com.example.authorizationservice.cell.validationСheck.NestedValid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleCheckbox {

    @NotNull(message = "size is null")
    private String size;

    @NotNull(message = "color is null")
    private String color;

    public String toString() {
        return "Checkbox{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
