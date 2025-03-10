package com.example.authorizationservice.cell.model.table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NotNull
public class Row {

    @NotNull(message = "id is null")
    private Long id;

    @NotNull(message = "data is null")
    private String data;

}
