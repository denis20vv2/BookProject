package com.example.authorizationservice.cell.InterfaceElement.filters;

import com.example.authorizationservice.cell.InterfaceElement.dropdown.ValidationDropdown;
import com.example.authorizationservice.cell.validationСheck.NestedValid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.authorizationservice.cell.InterfaceElement.form.Validation;

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
    @NestedValid
    private Value values;

    @NotNull(message = "validation is null")
    @NestedValid
    private ValidationDropdown validation;

    @Override
    public String toString() {
        return "FilterData{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", values=" + values +
                ", validation=" + validation +
                '}';
    }

}
