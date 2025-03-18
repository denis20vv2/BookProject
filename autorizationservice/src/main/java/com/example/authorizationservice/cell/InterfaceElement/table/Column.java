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
public class Column {

    @NotNull(message = "id is null")
    private String key;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "type is null")
    private String type;

    @NotNull(message = "filterable is null")
    private boolean filterable;

    @NotNull(message = "sortable is null")
    private boolean sortable;

    @NotNull(message = "searchable is null")
    private boolean searchable;

    @Override
    public String toString() {
        return "Column{" +
                "id='" + key + '\'' +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", filterable=" + filterable +
                ", sortable=" + sortable +
                ", searchable=" + searchable +
                '}';
    }

}
