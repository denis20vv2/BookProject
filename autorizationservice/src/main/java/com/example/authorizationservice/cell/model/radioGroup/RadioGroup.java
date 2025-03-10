package com.example.authorizationservice.cell.model.radioGroup;

import com.example.authorizationservice.cell.model.dropdown.Option;
import com.example.authorizationservice.cell.model.form.Validation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RadioGroup {

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "required is null")
    private boolean required;

    @NotNull(message = "options is null")
    private List<Option> options = new ArrayList<>();

    @NotNull(message = "selected is null")
    private String selected;

    @NotNull(message = "validation is null")
    private Validation validation;

    @NotNull(message = "styles is null")
    private Style styles;

}
