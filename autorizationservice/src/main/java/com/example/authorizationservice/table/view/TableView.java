package com.example.authorizationservice.table.view;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableView {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}
