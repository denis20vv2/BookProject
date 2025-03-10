package com.example.authorizationservice.cell.model.table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Table {

    @NotNull(message = "columns is null")
    private List<Column> columns =new ArrayList<>();

    @NotNull(message = "rows is null")
    private List<Row> rows =new ArrayList<>();

    @NotNull(message = "pagination is null")
    private Pagination pagination;

    @NotNull(message = "filter is null")
    private String filter;

    @NotNull(message = "search is null")
    private String search;


}
