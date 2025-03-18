package com.example.authorizationservice.cell.InterfaceElement.form;

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
public class Field {

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "type is null")
    private String type;

    @NotNull(message = "placeholder is null")
    private String placeholder;

    @NotNull(message = "required is null")
    private boolean required;

    @NotNull(message = "maxLength is null")
    private int maxLength;

    @NotNull(message = "validation is null")
    @NestedValid
    private Validation validation;

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", required=" + required +
                ", maxLength=" + maxLength +
                ", validation=" + validation +
                '}';
    }

}
