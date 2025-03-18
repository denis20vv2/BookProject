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
public class Row {

    @NotNull(message = "id is null")
    private Long id;

    @NotNull(message = "data is null")
    private String data;

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }

}
