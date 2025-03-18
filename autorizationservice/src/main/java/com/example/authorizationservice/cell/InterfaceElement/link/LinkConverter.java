package com.example.authorizationservice.cell.InterfaceElement.link;
import java.util.Map;

public class LinkConverter {

    public static Link convertToLink(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Link link = new Link();

            link.setText((String) map.get("text"));
            link.setUrl((String) map.get("url"));
            link.setDynamicParams((String) map.get("dynamicParams"));
            link.setTarget((String) map.get("target"));

            if (map.containsKey("icon") && map.get("icon") instanceof Map) {
                Map<String, Object> iconMap = (Map<String, Object>) map.get("icon");
                Icon icon = convertToIcon(iconMap);
                link.setIcon(icon);
            }

            if (map.containsKey("style") && map.get("style") instanceof Map) {
                Map<String, Object> styleMap = (Map<String, Object>) map.get("style");
                StyleLink styleLink = convertToStyle(styleMap);
                link.setStyle(styleLink);
            }

            return link;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

    public static Icon convertToIcon(Map<String, Object> iconMap) {
        Icon icon = new Icon();
        icon.setType((String) iconMap.get("type"));
        icon.setSrc((String) iconMap.get("src"));
        icon.setAlt((String) iconMap.get("alt"));
        return icon;
    }

    public static StyleLink convertToStyle(Map<String, Object> styleMap) {
        StyleLink styleLink = new StyleLink();
        styleLink.setColor((String) styleMap.get("color"));
        styleLink.setTextDecoration((String) styleMap.get("textDecoration"));
        styleLink.setFontSize((String) styleMap.get("fontSize"));
        return styleLink;
    }

}