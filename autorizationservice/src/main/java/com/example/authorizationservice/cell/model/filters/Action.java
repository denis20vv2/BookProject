package com.example.authorizationservice.cell.model.filters;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    @NotNull(message = "apply is null")
    private ExistAction apply;

    @NotNull(message = "filters is null")
    private ExistAction reset;

}
