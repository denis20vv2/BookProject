package com.example.authorizationservice.cell.InterfaceElement.radioGroup;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.Option;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.ValidationDropdown;
import com.example.authorizationservice.cell.InterfaceElement.form.Validation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RadioGroupConverter {

    public static RadioGroup convertToRadioGroup(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            RadioGroup radioGroup = new RadioGroup();

            radioGroup.setId((String) map.get("id"));
            radioGroup.setLabel((String) map.get("label"));
            radioGroup.setRequired((Boolean) map.get("required"));
            radioGroup.setSelected((String) map.get("selected"));

            if (map.containsKey("options") && map.get("options") instanceof List) {
                List<Map<String, Object>> optionsList = (List<Map<String, Object>>) map.get("options");
                List<Option> options = optionsList.stream()
                        .map(RadioGroupConverter::convertToOption)
                        .collect(Collectors.toList());
                radioGroup.setOptions(options);
            }

            if (map.containsKey("validation") && map.get("validation") instanceof Map) {
                Map<String, Object> validationMap = (Map<String, Object>) map.get("validation");
                ValidationDropdown validation = convertToValidation(validationMap);
                radioGroup.setValidation(validation);
            }

            if (map.containsKey("styles") && map.get("styles") instanceof Map) {
                Map<String, Object> stylesMap = (Map<String, Object>) map.get("styles");
                StyleRadio styles = convertToStyleRadio(stylesMap);
                radioGroup.setStyles(styles);
            }

            return radioGroup;
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
        ValidationDropdown validation = new ValidationDropdown();
        validation.setRequired((boolean) validationMap.get("required"));
        validation.setErrorMessage((String) validationMap.get("errorMessage"));
        return validation;
    }

    public static StyleRadio convertToStyleRadio(Map<String, Object> styleMap) {
        StyleRadio styleRadio = new StyleRadio();
        styleRadio.setDirection((String) styleMap.get("direction"));
        styleRadio.setColor((String) styleMap.get("color"));
        styleRadio.setSize((String) styleMap.get("size"));
        return styleRadio;
    }
}
