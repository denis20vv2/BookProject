package com.example.authorizationservice.cell.InterfaceElement.table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterTable {

    @NotNull(message = "name is null")
    private String name;

    @NotNull(message = "name is null")
    private String age;

    @NotNull(message = "name is null")
    private String role;

    @Override
    public String toString() {
        return "Table{" +
                "columns=" + name +
                ", rows=" + age +
                ", pagination=" + role +
                '}';
    }

}

