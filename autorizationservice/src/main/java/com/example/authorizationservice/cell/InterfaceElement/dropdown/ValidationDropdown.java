package com.example.authorizationservice.cell.InterfaceElement.dropdown;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDropdown {

    @NotNull(message = "regex is null")
    private boolean required;

    @NotNull(message = "errorMessage is null")
    private String errorMessage;

    @Override
    public String toString() {
        return "Validation{" +
                "regex='" + required + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
