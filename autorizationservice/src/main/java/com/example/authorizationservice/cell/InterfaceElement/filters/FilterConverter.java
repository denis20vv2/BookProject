package com.example.authorizationservice.cell.InterfaceElement.filters;

import com.example.authorizationservice.cell.InterfaceElement.dropdown.ValidationDropdown;
import com.example.authorizationservice.cell.InterfaceElement.form.Validation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterConverter {

    public static Filter convertToFilter(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Filter filter = new Filter();

            filter.setId((String) map.get("id"));
            filter.setLabel((String) map.get("label"));

            if (map.containsKey("filters") && map.get("filters") instanceof List) {
                List<Map<String, Object>> filtersList = (List<Map<String, Object>>) map.get("filters");
                List<FilterData> filters = filtersList.stream()
                        .map(filterMap -> convertToFilterData(filterMap))
                        .collect(Collectors.toList());
                filter.setFilters(filters);
            }

            if (map.containsKey("action") && map.get("action") instanceof Map) {
                Map<String, Object> actionMap = (Map<String, Object>) map.get("action");
                ActionFilter action = convertToActionFilter(actionMap);
                filter.setAction(action);
            }

            return filter;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

    public static FilterData convertToFilterData(Map<String, Object> filterMap) {
        FilterData filterData = new FilterData();
        filterData.setId((String) filterMap.get("id"));
        filterData.setLabel((String) filterMap.get("label"));
        filterData.setType((String) filterMap.get("type"));
        filterData.setPlaceholder((String) filterMap.get("placeholder"));

        // Преобразуем значения
        if (filterMap.containsKey("values") && filterMap.get("values") instanceof Map) {
            Map<String, Object> valuesMap = (Map<String, Object>) filterMap.get("values");
            Value values = convertToValue(valuesMap);
            filterData.setValues(values);
        }

        // Преобразуем валидацию
        if (filterMap.containsKey("validation") && filterMap.get("validation") instanceof Map) {
            Map<String, Object> validationMap = (Map<String, Object>) filterMap.get("validation");
            ValidationDropdown validation = convertToValidation(validationMap);
            filterData.setValidation(validation);
        }

        return filterData;
    }

    public static Value convertToValue(Map<String, Object> valueMap) {
        Value value = new Value();
        value.setFrom((String) valueMap.get("from"));
        value.setTo((String) valueMap.get("to"));
        return value;
    }

    public static ActionFilter convertToActionFilter(Map<String, Object> actionMap) {
        ActionFilter actions = new ActionFilter();

        // Преобразуем действия apply и reset
        if (actionMap.containsKey("apply") && actionMap.get("apply") instanceof Map) {
            Map<String, Object> applyMap = (Map<String, Object>) actionMap.get("apply");
            ExistAction apply = convertToExistAction(applyMap);
            actions.setApply(apply);
        }

        if (actionMap.containsKey("reset") && actionMap.get("reset") instanceof Map) {
            Map<String, Object> resetMap = (Map<String, Object>) actionMap.get("reset");
            ExistAction reset = convertToExistAction(resetMap);
            actions.setReset(reset);
        }

        return actions;
    }

    public static ExistAction convertToExistAction(Map<String, Object> actionMap) {
        ExistAction action = new ExistAction();
        action.setText((String) actionMap.get("text"));
        action.setAction((String) actionMap.get("action"));
        action.setMethod((String) actionMap.get("method"));
        return action;
    }

    public static ValidationDropdown convertToValidation(Map<String, Object> validationMap) {
        ValidationDropdown validation = new ValidationDropdown();
        validation.setRequired((boolean) validationMap.get("required"));
        validation.setErrorMessage((String) validationMap.get("errorMessage"));
        return validation;
    }
}

