package com.example.authorizationservice.cell.InterfaceElement.Button;

import java.util.Map;

public class ButtonConverter {
    public static Button convertToButton(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Button button = new Button();
            button.setLabel((String) map.get("label"));
            button.setId((String) map.get("id"));
            button.setImageUrl((String) map.get("imageUrl"));

            if (map.containsKey("action") && map.get("action") instanceof Map) {
                Map<String, Object> actionData = (Map<String, Object>) map.get("action");
                Action action = convertToAction(actionData);
                button.setAction(action);
            }

            return button;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

    public static Action convertToAction(Map<String, Object> actionMap) {
        Action action = new Action();
        action.setType((String) actionMap.get("type"));
        action.setUrl((String) actionMap.get("url"));


        if (actionMap.containsKey("style") && actionMap.get("style") instanceof Map) {
            Map<String, Object> styleMap = (Map<String, Object>) actionMap.get("style");
            Style style = convertToStyle(styleMap);  // Исправлено: теперь метод принимает Map<String, Object>
            action.setStyle(style);
        }

        return action;
    }

    public static Style convertToStyle(Map<String, Object> styleMap) {
        Style style = new Style();
        style.setWidth((String) styleMap.get("width"));
        style.setHeight((String) styleMap.get("height"));
        style.setBackgroundColor((String) styleMap.get("backgroundColor"));
        style.setTextColor((String) styleMap.get("textColor"));
        style.setBorderRadius((String) styleMap.get("borderRadius"));

        return style;
    }
}