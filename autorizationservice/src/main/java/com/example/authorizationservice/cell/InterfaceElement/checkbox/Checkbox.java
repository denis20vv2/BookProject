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
public class Checkbox {

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "checked is null")
    private boolean checked;

    @NotNull(message = "disabled is null")
    private boolean disabled;

    @NotNull(message = "indeterminate is null")
    private boolean indeterminate;

    @NotNull(message = "id is null")
    @NestedValid
    private StyleCheckbox style;

    @Override
    public String toString() {
        return "Checkbox{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", checked='" + checked + '\'' +
                ", disabled='" + disabled + '\'' +
                ", indeterminate='" + indeterminate + '\'' +
                ", styles=" + style +
                '}';
    }
}


