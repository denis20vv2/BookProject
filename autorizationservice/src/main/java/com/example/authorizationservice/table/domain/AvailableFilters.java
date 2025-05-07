package com.example.authorizationservice.table.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableFilters {

    @NotNull(message = "name = null")
    private String name;

    @NotNull(message = "label = null")
    private String label;

    @NotNull(message = "filters = null")
    private List<Filter> filters;

}
