package com.example.authorizationservice.cell.InterfaceElement.dropdown;

import com.example.authorizationservice.cell.validationСheck.NestedValid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dropdown {

    @NotNull(message = "type is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "placeholder is null")
    private String placeholder;

    @NotNull(message = "searchable is null")
    private boolean searchable;

    @NotNull(message = "allowCustomInput is null")
    private boolean allowCustomInput;

    @NotNull(message = "multiple is null")
    private boolean multiple;

    @NotNull(message = "options is null")
    @NestedValid
    private List<Option> options;

    @NotNull(message = "selected is null")
    private String selected;

    @NotNull(message = "validation is null")
    @NestedValid
    private ValidationDropdown validation;

    @NotNull(message = "styles is null")
    @NestedValid
    private List<StyleDropdown> styles = new ArrayList<>();

    @Override
    public String toString() {
        return "Dropdown{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", searchable=" + searchable +
                ", allowCustomInput=" + allowCustomInput +
                ", multiple=" + multiple +
                ", options=" + options +
                ", selected='" + selected + '\'' +
                ", validation=" + validation +
                ", styles=" + styles +
                '}';
    }

}
