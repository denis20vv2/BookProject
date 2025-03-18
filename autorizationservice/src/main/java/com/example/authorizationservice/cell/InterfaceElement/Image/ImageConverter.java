package com.example.authorizationservice.cell.InterfaceElement.Image;

import com.example.authorizationservice.cell.InterfaceElement.Container.Container;
import com.example.authorizationservice.cell.InterfaceElement.Frame.Frame;
import com.example.authorizationservice.cell.InterfaceElement.checkbox.StyleCheckbox;

import java.util.Map;

public class ImageConverter {
    public static Image convertToImage(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Image image = new Image();
            image.setId((String) map.get("id"));
            image.setSrc((String) map.get("src"));
            image.setAlt((String) map.get("alt"));

            if (map.containsKey("style") && map.get("style") instanceof Map) {
                Map<String, Object> styleData = (Map<String, Object>) map.get("style");
                StyleImage styleImage = convertToStyleImage(styleData);
                image.setStyle(styleImage);
            }

            return image;
        }
        throw new IllegalArgumentException("Invalid object type for Image");
    }

    public static StyleImage convertToStyleImage(Map<String, Object> styleMap) {
        StyleImage styleImage = new StyleImage();
        styleImage.setWidth((String) styleMap.get("width"));
        styleImage.setHeight((String) styleMap.get("height"));
        styleImage.setBorderRadius((String) styleMap.get("borderRadius"));
        styleImage.setBoxShadow((String) styleMap.get("boxShadow"));
        return styleImage;
    }
}
