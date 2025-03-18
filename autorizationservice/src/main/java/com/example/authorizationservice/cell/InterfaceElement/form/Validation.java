package com.example.authorizationservice.cell.InterfaceElement.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Validation {

    @NotNull(message = "regex is null")
    private String regex;

    @NotNull(message = "errorMessage is null")
    private String errorMessage;

    @Override
    public String toString() {
        return "Validation{" +
                "regex='" + regex + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

}
