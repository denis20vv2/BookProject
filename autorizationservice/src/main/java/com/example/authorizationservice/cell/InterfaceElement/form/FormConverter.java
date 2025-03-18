package com.example.authorizationservice.cell.InterfaceElement.form;
import java.util.*;
import java.util.stream.Collectors;

public class FormConverter {

    public static Form convertToForm(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Form form = new Form();

            if (map.containsKey("fields") && map.get("fields") instanceof List) {
                List<Map<String, Object>> fieldsList = (List<Map<String, Object>>) map.get("fields");
                List<Field> fields = fieldsList.stream()
                        .map(FormConverter::convertToField)
                        .collect(Collectors.toList());
                form.setFields(fields);
            }

            if (map.containsKey("submit") && map.get("submit") instanceof Map) {
                Map<String, Object> submitMap = (Map<String, Object>) map.get("submit");
                Submit submit = convertToSubmit(submitMap);
                form.setSubmit(submit);
            }

            return form;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

    public static Field convertToField(Map<String, Object> fieldMap) {
        Field field = new Field();
        field.setId((String) fieldMap.get("id"));
        field.setLabel((String) fieldMap.get("label"));
        field.setType((String) fieldMap.get("type"));
        field.setPlaceholder((String) fieldMap.get("placeholder"));
        field.setRequired((Boolean) fieldMap.get("required"));
        field.setMaxLength((Integer) fieldMap.get("maxLength"));

        if (fieldMap.containsKey("validation") && fieldMap.get("validation") instanceof Map) {
            Map<String, Object> validationMap = (Map<String, Object>) fieldMap.get("validation");
            Validation validation = convertToValidation(validationMap);
            field.setValidation(validation);
        }

        return field;
    }

    public static Submit convertToSubmit(Map<String, Object> submitMap) {
        Submit submit = new Submit();
        submit.setText((String) submitMap.get("text"));
        submit.setAction((String) submitMap.get("action"));
        submit.setMethod((String) submitMap.get("method"));
        return submit;
    }

    public static Validation convertToValidation(Map<String, Object> validationMap) {
        Validation validation = new Validation();
        validation.setRegex((String) validationMap.get("regex"));
        validation.setErrorMessage((String) validationMap.get("errorMessage"));
        return validation;
    }
}
