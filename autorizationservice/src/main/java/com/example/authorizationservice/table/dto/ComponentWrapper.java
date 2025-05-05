package com.example.authorizationservice.table.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentWrapper {

    @NotBlank(message = "Поле 'component' обязательно")
    private String component;

    @NotNull(message = "Поле 'data' обязательно")
    private JsonNode data;

}
