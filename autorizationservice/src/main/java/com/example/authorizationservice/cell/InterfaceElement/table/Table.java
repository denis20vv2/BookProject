package com.example.authorizationservice.cell.InterfaceElement.table;

import com.example.authorizationservice.cell.validationСheck.NestedValid;
import jakarta.validation.Valid;
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
    @NestedValid
    @Valid
    private List<Column> columns =new ArrayList<>();

    @NotNull(message = "rows is null")
    @NestedValid
    @Valid
    private List<Row> rows =new ArrayList<>();

    @NotNull(message = "pagination is null")
    @NestedValid
    private Pagination pagination;

    @NotNull(message = "filter is null")
    @NestedValid
    private FilterTable filter;

    @NotNull(message = "search is null")
    private String search;
    @Override
    public String toString() {
        return "Table{" +
                "columns=" + columns +
                ", rows=" + rows +
                ", pagination=" + pagination +
                ", filter='" + filter + '\'' +
                ", search='" + search + '\'' +
                '}';
    }

}
