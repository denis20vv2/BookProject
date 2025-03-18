package com.example.authorizationservice.cell.InterfaceElement.filters;

import com.example.authorizationservice.cell.validationСheck.NestedValid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "filters is null")
    @NestedValid
    private List<FilterData> filters;

    @NotNull(message = "actions is null")
    @NestedValid
    private ActionFilter action;

    @Override
    public String toString() {
        return "Filter{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", filters=" + filters +
                ", actions=" + action +
                '}';
    }

}
