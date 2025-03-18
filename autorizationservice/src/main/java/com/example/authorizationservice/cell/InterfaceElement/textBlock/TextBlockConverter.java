package com.example.authorizationservice.cell.InterfaceElement.textBlock;
import java.util.Map;

public class TextBlockConverter {

    public static TextBlock convertToTextBlock(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            TextBlock textBlock = new TextBlock();

            textBlock.setId((String) map.get("id"));
            textBlock.setLabel((String) map.get("label"));
            textBlock.setValue((String) map.get("value"));
            textBlock.setType((String) map.get("type"));

            if (map.containsKey("styles") && map.get("styles") instanceof Map) {
                Map<String, Object> stylesMap = (Map<String, Object>) map.get("styles");
                StyleTextBlock styles = convertToStyleTextBlock(stylesMap);
                textBlock.setStyles(styles);
            }

            return textBlock;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

    public static StyleTextBlock convertToStyleTextBlock(Map<String, Object> stylesMap) {
        StyleTextBlock styles = new StyleTextBlock();
        styles.setFontSize((String) stylesMap.get("fontSize"));
        styles.setColor((String) stylesMap.get("color"));
        styles.setFontWeight((String) stylesMap.get("fontWeight"));
        return styles;
    }
}
