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

            dropdown.setId((String) map.get("id"));
            dropdown.setLabel((String) map.get("label"));
            dropdown.setPlaceholder((String) map.get("placeholder"));
            dropdown.setSearchable((boolean) map.get("searchable"));
            dropdown.setAllowCustomInput((boolean) map.get("allowCustomInput"));
            dropdown.setMultiple((boolean) map.get("multiple"));
            dropdown.setSelected((String) map.get("selected"));


            if (map.containsKey("options") && map.get("options") instanceof List) {
                List<Map<String, Object>> optionsList = (List<Map<String, Object>>) map.get("options");
                List<Option> options = optionsList.stream()
                        .map(optionMap -> convertToOption(optionMap))
                        .collect(Collectors.toList());
                dropdown.setOptions(options);
            }


            if (map.containsKey("validation") && map.get("validation") instanceof Map) {
                Map<String, Object> validationMap = (Map<String, Object>) map.get("validation");
                ValidationDropdown validationDropdown = convertToValidation(validationMap);
                dropdown.setValidation(validationDropdown);
            }


            if (map.containsKey("styles") && map.get("styles") instanceof List) {
                List<Map<String, Object>> stylesList = (List<Map<String, Object>>) map.get("styles");
                List<StyleDropdown> styles = stylesList.stream()
                        .map(styleMap -> convertToStyleDropdown(styleMap))
                        .collect(Collectors.toList());
                dropdown.setStyles(styles);
            }

            return dropdown;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

    public static Option convertToOption(Map<String, Object> optionMap) {
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
    }
}

