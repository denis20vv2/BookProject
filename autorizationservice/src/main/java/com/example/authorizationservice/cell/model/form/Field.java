package com.example.authorizationservice.cell.model.form;

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
    private Validation validation;
}
