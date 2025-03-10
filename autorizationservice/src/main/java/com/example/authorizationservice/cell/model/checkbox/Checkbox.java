package com.example.authorizationservice.cell.model.checkbox;

import com.example.authorizationservice.cell.model.link.Styles;
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
    private String checked;

    @NotNull(message = "disabled is null")
    private String disabled;

    @NotNull(message = "indeterminate is null")
    private String indeterminate;

    @NotNull(message = "id is null")
    private Styles styles;


}
