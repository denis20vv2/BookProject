package com.example.authorizationservice.table.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {

    @NotNull(message = "accessor is null")
    private String accessor;

    @NotNull(message = "header is null")
    private String header;

}
