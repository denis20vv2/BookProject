package com.example.authorizationservice.cell.InterfaceElement.textBlock;

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
public class TextBlock implements InterfaceElement {

    @NotNull(message = "type is null")
    private String id;

    @NotNull(message = "type is null")
    private String type;

    @NotNull(message = "value is null")
    private String value;



    @Override
    public String toString() {
        return "TextBlock{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", value='" + type + '\'' +
                '}';
    }

}
