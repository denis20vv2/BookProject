package com.example.authorizationservice.cell.InterfaceElement.Container;


import com.example.authorizationservice.cell.InterfaceElement.checkbox.Checkbox;
import com.example.authorizationservice.cell.InterfaceElement.checkbox.StyleCheckbox;

import java.util.Map;

public class ContainerConverter {
    public static Container convertToContainer(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Container container = new Container();
            container.setId((String) map.get("id"));
            container.setLayout((String) map.get("layout"));
            container.setDirection((String) map.get("direction"));
            container.setAlignItems((String) map.get("alignItems"));
            container.setJustifyContent((String) map.get("justifyContent"));

            if (map.containsKey("style") && map.get("style") instanceof Map) {
                Map<String, Object> stylesData = (Map<String, Object>) map.get("style");
                ContainerStyle containerStyle = convertToContainerStyle(stylesData);
                container.setStyle(containerStyle);
            }

            return container;
        }
        throw new IllegalArgumentException("Invalid object type for Container");
    }

    public static ContainerStyle convertToContainerStyle(Map<String, Object> styleMap) {
        ContainerStyle containerStyle = new ContainerStyle();
        containerStyle.setPadding((String) styleMap.get("padding"));
        containerStyle.setBackgroundColor((String) styleMap.get("backgroundColor"));
        containerStyle.setBoxShadow((String) styleMap.get("boxShadow"));
        containerStyle.setBorderRadius((String) styleMap.get("borderRadius"));
        return containerStyle;
    }
}
