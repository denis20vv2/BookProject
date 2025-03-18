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
public class Submit {

    @NotNull(message = "text is null")
    private String text;

    @NotNull(message = "action is null")
    private String action;

    @NotNull(message = "method is null")
    private String method;

    @Override
    public String toString() {
        return "Submit{" +
                "text='" + text + '\'' +
                ", action='" + action + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

}
