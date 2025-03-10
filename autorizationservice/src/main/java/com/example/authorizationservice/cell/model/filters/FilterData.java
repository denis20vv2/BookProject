package com.example.authorizationservice.cell.model.filters;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.authorizationservice.cell.model.form.Validation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterData {

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "type is null")
    private String type;

    @NotNull(message = "placeholder is null")
    private String placeholder;

    @NotNull(message = "values is null")
    private Value values;

    @NotNull(message = "validation is null")
    private Validation validation;

}
