package com.example.authorizationservice.cell.InterfaceElement.Frame;

import com.example.authorizationservice.cell.InterfaceElement.Container.Container;
import com.example.authorizationservice.cell.InterfaceElement.checkbox.StyleCheckbox;

import java.util.Map;

public class FrameConverter {
    public static Frame convertToFrame(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Frame frame = new Frame();
            frame.setId((String) map.get("id"));
            frame.setUrl((String) map.get("url"));
            frame.setSandbox((boolean) map.get("sandbox"));

            if (map.containsKey("style") && map.get("style") instanceof Map) {
                Map<String, Object> styleData = (Map<String, Object>) map.get("style");
                StyleFrame styleFrame = convertToStyleFrame(styleData);
                frame.setStyle(styleFrame);
            }

            return frame;
        }
        throw new IllegalArgumentException("Invalid object type for Frame");
    }

    public static StyleFrame convertToStyleFrame(Map<String, Object> styleMap) {
        StyleFrame styleFrame = new StyleFrame();
        styleFrame.setWidth((String) styleMap.get("width"));
        styleFrame.setHeight((String) styleMap.get("height"));
        styleFrame.setBorder((String) styleMap.get("border"));
        return styleFrame;
    }
}
