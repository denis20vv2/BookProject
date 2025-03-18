package com.example.authorizationservice.cell.InterfaceElement.checkbox;


import java.util.Map;

public class CheckboxConverter {

    public static Checkbox convertToCheckbox(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Checkbox checkbox = new Checkbox();
            checkbox.setId((String) map.get("id"));
            checkbox.setLabel((String) map.get("label"));
            checkbox.setChecked((boolean) map.get("checked"));
            checkbox.setDisabled((boolean) map.get("disabled"));
            checkbox.setIndeterminate((boolean) map.get("indeterminate"));

            if (map.containsKey("style") && map.get("style") instanceof Map) {
                Map<String, Object> stylesData = (Map<String, Object>) map.get("style");
                StyleCheckbox styleCheckbox = convertToStyleCheckbox(stylesData);
                checkbox.setStyle(styleCheckbox);
            }

            return checkbox;
        }
        throw new IllegalArgumentException("Invalid object type for Checkbox");
    }

    public static StyleCheckbox convertToStyleCheckbox(Map<String, Object> styleMap) {
        StyleCheckbox styleCheckbox = new StyleCheckbox();
        styleCheckbox.setColor((String) styleMap.get("color"));
        styleCheckbox.setSize((String) styleMap.get("size"));
        return styleCheckbox;
    }
}
