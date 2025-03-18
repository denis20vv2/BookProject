package com.example.authorizationservice.cell.InterfaceElement.radioGroup;

import com.example.authorizationservice.cell.InterfaceElement.dropdown.Option;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.ValidationDropdown;
import com.example.authorizationservice.cell.validationСheck.NestedValid;
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
    @NestedValid
    private List<Option> options = new ArrayList<>();

    @NotNull(message = "selected is null")
    private String selected;

    @NotNull(message = "validation is null")
    @NestedValid
    private ValidationDropdown validation;

    @NotNull(message = "styles is null")
    @NestedValid
    private StyleRadio styles;

    @Override
    public String toString() {
        return "RadioGroup{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", required=" + required +
                ", options=" + options +
                ", selected='" + selected + '\'' +
                ", validation=" + validation +
                ", styles=" + styles +
                '}';
    }

}
