package com.example.authorizationservice.cell.InterfaceElement.dropdown;
import com.example.authorizationservice.cell.InterfaceElement.form.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DropdownConverter {

    public static Dropdown convertToDropdown(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Dropdown dropdown = new Dropdown();

            dropdown.setValue((String) map.get("value"));
            dropdown.setId((String) map.get("id"));
            dropdown.setType((String) map.get("type"));
            dropdown.setActive((Boolean) map.get("active"));

            if (map.containsKey("optionsList") && map.get("optionsList") instanceof List<?>) {
                List<?> rawList = (List<?>) map.get("optionsList");
                List<String> optionsList = rawList.stream()
                        .filter(item -> item instanceof String)
                        .map(item -> (String) item)
                        .collect(Collectors.toList());

                dropdown.setOptionsList(optionsList);
            }

            return dropdown;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

  /*  public static Option convertToOption(Map<String, Object> optionMap) {
        Option option = new Option();
        option.setId((String) optionMap.get("id"));
        option.setLabel((String) optionMap.get("label"));
        option.setValue((String) optionMap.get("value"));
        return option;
    }

    public static ValidationDropdown convertToValidation(Map<String, Object> validationMap) {
        ValidationDropdown validationDropdown = new ValidationDropdown();
        validationDropdown.setRequired((boolean) validationMap.get("required"));
        validationDropdown.setErrorMessage((String) validationMap.get("errorMessage"));
        return validationDropdown;
    }

    public static StyleDropdown convertToStyleDropdown(Map<String, Object> styleMap) {
        StyleDropdown style = new StyleDropdown();
        style.setWidth((String) styleMap.get("width"));
        style.setBorderRadius((String) styleMap.get("borderRadius"));
        style.setBackgroundColor((String) styleMap.get("backgroundColor"));
        style.setBorderColor((String) styleMap.get("borderColor"));
        return style;
    }*/
}

