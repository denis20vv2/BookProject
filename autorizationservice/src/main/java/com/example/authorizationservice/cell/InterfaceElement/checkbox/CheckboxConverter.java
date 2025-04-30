package com.example.authorizationservice.cell.InterfaceElement.checkbox;


import java.util.Map;

public class CheckboxConverter {

    public static Checkbox convertToCheckbox(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Checkbox checkbox = new Checkbox();
            checkbox.setId((String) map.get("id"));
            checkbox.setValue((String) map.get("value"));
            checkbox.setType((String) map.get("type"));

            /*if (map.containsKey("style") && map.get("style") instanceof Map) {
                Map<String, Object> stylesData = (Map<String, Object>) map.get("style");
                StyleCheckbox styleCheckbox = convertToStyleCheckbox(stylesData);
                checkbox.setStyle(styleCheckbox);
            }
*/
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
