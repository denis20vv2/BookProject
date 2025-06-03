package com.example.authorizationservice.cell.InterfaceElement.checkbox;

import com.example.authorizationservice.cell.InterfaceElement.InterfaceElement.InterfaceElement;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checkbox implements InterfaceElement {

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "label is null")
    private String value;

    @NotNull(message = "checked is null")
    private String type;


    @Override
    public String toString() {
        return "Checkbox{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}


