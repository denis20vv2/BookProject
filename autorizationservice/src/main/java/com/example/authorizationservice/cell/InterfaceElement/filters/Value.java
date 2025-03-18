package com.example.authorizationservice.cell.InterfaceElement.filters;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Value {

    @NotNull(message = "from is null")
    private String from;

    @NotNull(message = "to is null")
    private String to;

    @Override
    public String toString() {
        return "Value{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }

}

