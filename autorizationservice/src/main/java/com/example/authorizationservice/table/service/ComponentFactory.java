package com.example.authorizationservice.table.service;

import com.example.authorizationservice.cell.InterfaceElement.checkbox.Checkbox;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.Dropdown;
import com.example.authorizationservice.cell.InterfaceElement.textBlock.TextBlock;
import com.example.authorizationservice.cell.InterfaceElement.InterfaceElement.InterfaceElement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ComponentFactory {

    private final ObjectMapper objectMapper;

    public ComponentFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    ///////////////добавить валидацию проверок id внутри объектов!

    public InterfaceElement parse(String type, JsonNode data) {
        switch (type) {
            case "TextBlock":
                return objectMapper.convertValue(data, TextBlock.class);
            case "Dropdown":
                return objectMapper.convertValue(data, Dropdown.class);
            case "Checkbox":
                return objectMapper.convertValue(data, Checkbox.class);
            default:
                throw new IllegalArgumentException("Неизвестный компонент: " + type);
        }
    }
}
