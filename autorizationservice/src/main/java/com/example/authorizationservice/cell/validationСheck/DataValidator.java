package com.example.authorizationservice.cell.validationСheck;

import com.example.authorizationservice.cell.InterfaceElement.Button.Action;
import com.example.authorizationservice.cell.InterfaceElement.Button.Button;
import com.example.authorizationservice.cell.InterfaceElement.Button.ButtonConverter;
import com.example.authorizationservice.cell.InterfaceElement.Container.Container;
import com.example.authorizationservice.cell.InterfaceElement.Container.ContainerConverter;
import com.example.authorizationservice.cell.InterfaceElement.Frame.Frame;
import com.example.authorizationservice.cell.InterfaceElement.Frame.FrameConverter;
import com.example.authorizationservice.cell.InterfaceElement.Image.Image;
import com.example.authorizationservice.cell.InterfaceElement.Image.ImageConverter;
import com.example.authorizationservice.cell.InterfaceElement.checkbox.Checkbox;
import com.example.authorizationservice.cell.InterfaceElement.checkbox.CheckboxConverter;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.Dropdown;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.DropdownConverter;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.Option;
//import com.example.authorizationservice.cell.InterfaceElement.filters.Filter;
//import com.example.authorizationservice.cell.InterfaceElement.filters.FilterConverter;
//import com.example.authorizationservice.cell.InterfaceElement.filters.FilterData;
import com.example.authorizationservice.cell.InterfaceElement.form.Form;
import com.example.authorizationservice.cell.InterfaceElement.form.FormConverter;
import com.example.authorizationservice.cell.InterfaceElement.link.Link;
import com.example.authorizationservice.cell.InterfaceElement.link.LinkConverter;
import com.example.authorizationservice.cell.InterfaceElement.radioGroup.RadioGroup;
import com.example.authorizationservice.cell.InterfaceElement.radioGroup.RadioGroupConverter;
import com.example.authorizationservice.cell.InterfaceElement.table.Table;
import com.example.authorizationservice.cell.InterfaceElement.table.TableConverter;
import com.example.authorizationservice.cell.InterfaceElement.textBlock.TextBlock;
import com.example.authorizationservice.cell.InterfaceElement.textBlock.TextBlockConverter;
import com.example.authorizationservice.page.domain.CellObject;
import com.example.authorizationservice.page.domain.Data;
import com.example.authorizationservice.user.web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.*;

import java.util.Map;


import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Component
public class DataValidator implements ConstraintValidator<ValidData, Data> {

    private Validator validator;

    private final ObjectMapper objectMapper;
    private final ButtonConverter buttonConverter;
    private final DropdownConverter dropdownConverter;
    //private final FilterConverter filterConverter;
    private final CheckboxConverter checkboxConverter;
    private final FormConverter formConverter;
    private final LinkConverter linkConverter;
    private final RadioGroupConverter radioGroupConverter;
    private final TableConverter tableConverter;
    private final TextBlockConverter textBlockConverter;
    private final ContainerConverter containerConverter;
    private final ImageConverter imageConverter;
    private final FrameConverter frameConverter;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public void initialize(ValidData constraintAnnotation) {

        if (this.validator == null) {
            this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
    }

    @Override
    public boolean isValid(Data data, ConstraintValidatorContext context) {
        List<CellObject> cellObjects = data.getCellObjects();

        /*if (cellObjects.isEmpty()) {
            return false;
        }*/

        for (CellObject cellObject : cellObjects) {
            Object object = cellObject.getData();

            if (cellObject.getKey() == null) {
                context.buildConstraintViolationWithTemplate("Data is not a valid (Key == null, data !=null) ")
                        .addPropertyNode("data")
                        .addConstraintViolation();
                return false;
            }

            if (object != null) {

                if (object instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> dataMap = (Map<String, Object>) object;
                    String type = dataMap.keySet().iterator().next();
                    Object rawValue = dataMap.get(type);

                    if (rawValue instanceof Map) {
                        Map<String, Object> valueMap = (Map<String, Object>) rawValue;
                        if (!validateDataByType(valueMap, type, context)) {
                           return false;
                        }

                    } else {
                        try {
                            Map<String, Object> valueMap = objectMapper.convertValue(rawValue, Map.class);
                            return validateDataByType(valueMap, type, context);

                        } catch (Exception e) {
                            context.buildConstraintViolationWithTemplate("Failed to convert raw value to Map for type: " + type)
                                    .addPropertyNode("data")
                                    .addConstraintViolation();
                            logger.error("Error converting raw value to Map: {}", e.getMessage());
                            return false;
                        }
                    }

                } else {
                    context.buildConstraintViolationWithTemplate("Data is not a valid map for cellObject with key: " + cellObject.getKey())
                            .addPropertyNode("data")
                            .addConstraintViolation();
                    return false;
                }
            } else {
                logger.error("Data is null, Key = " + cellObject.getKey());
                return true;
            }
        }
        return true;
    }

    private boolean validateDataByType(Map<String, Object> dataMap, String type, ConstraintValidatorContext context) {
        try {
            switch (type) {
                case "Button":
                    Button button = buttonConverter.convertToButton(dataMap);
                    return validateButton(button, context);
                case "Checkbox":
                    Checkbox checkbox = checkboxConverter.convertToCheckbox(dataMap);
                    return validateCheckbox(checkbox, context);
                case "Dropdown":
                    Dropdown dropdown = dropdownConverter.convertToDropdown(dataMap);
                    return validateDropdown(dropdown, context);
                /*case "Filter":
                    Filter filter = filterConverter.convertToFilter(dataMap);
                    return validateFilter(filter, context);*/
                case "Form":
                    Form form = formConverter.convertToForm(dataMap);
                    return validateForm(form, context);
                case "Link":
                    Link link = linkConverter.convertToLink(dataMap);
                    return validateLink(link, context);
                case "RadioGroup":
                    RadioGroup radioGroup = radioGroupConverter.convertToRadioGroup(dataMap);
                    return validateRadioGroup(radioGroup, context);
                case "Table":
                    Table table = tableConverter.convertToTable(dataMap);
                    return validateTable(table, context);
                case "TextBlock":
                    TextBlock textBlock = textBlockConverter.convertToTextBlock(dataMap);
                    return validateTextBlock(textBlock, context);
                case "Container":
                    Container container = containerConverter.convertToContainer(dataMap);
                    return validateContainer(container, context);
                case "Image":
                    Image image = imageConverter.convertToImage(dataMap);
                    return validateImage(image, context);
                case "Frame":
                    Frame frame = frameConverter.convertToFrame(dataMap);
                    return validateFrame(frame, context);
                default:
                    context.buildConstraintViolationWithTemplate("Invalid type: " + type)
                            .addPropertyNode("data")
                            .addConstraintViolation();
                    return false; // Неверный тип
            }
        } catch (Exception e) {
            logger.error("Error converting data for type {}: {}", type, e.getMessage());
            context.buildConstraintViolationWithTemplate("Invalid data structure for type: " + type)
                    .addPropertyNode("data")
                    .addConstraintViolation();
            return false;
        }
    }

    private boolean validateButton(Button button, ConstraintValidatorContext context) {
        logger.info("Before Validation - Button: {}", button);
        Set<ConstraintViolation<Button>> violations = validator.validate(button);
        return handleViolations(violations, context);
    }

    private boolean validateCheckbox(Checkbox checkbox, ConstraintValidatorContext context) {
        logger.info("Before Validation - checkbox: {}", checkbox);
        Set<ConstraintViolation<Checkbox>> violations = validator.validate(checkbox);
        return handleViolations(violations, context);
    }

    private boolean validateContainer(Container container, ConstraintValidatorContext context) {
        logger.info("Before Validation - container: {}", container);
        Set<ConstraintViolation<Container>> violations = validator.validate(container);
        return handleViolations(violations, context);
    }

    private boolean validateImage(Image image, ConstraintValidatorContext context) {
        logger.info("Before Validation - image: {}", image);
        Set<ConstraintViolation<Image>> violations = validator.validate(image);
        return handleViolations(violations, context);
    }

    private boolean validateFrame(Frame frame, ConstraintValidatorContext context) {
        logger.info("Before Validation - frame: {}", frame);
        Set<ConstraintViolation<Frame>> violations = validator.validate(frame);
        return handleViolations(violations, context);
    }

    private boolean validateDropdown(Dropdown dropdown, ConstraintValidatorContext context) {
        logger.info("Before Validation - Dropdown: {}", dropdown);
        Set<ConstraintViolation<Dropdown>> violations = validator.validate(dropdown);
        return handleViolations(violations, context);
    }

    /*private boolean validateFilter(Filter filter, ConstraintValidatorContext context) {
        logger.info("Before Validation - filter: {}", filter);
        Set<ConstraintViolation<Filter>> violations = validator.validate(filter);
        return handleViolations(violations, context);
    }*/

    private boolean validateForm(Form form, ConstraintValidatorContext context) {
        logger.info("Before Validation - form: {}", form);
        Set<ConstraintViolation<Form>> violations = validator.validate(form);
        return handleViolations(violations, context);
    }

    private boolean validateLink(Link link, ConstraintValidatorContext context) {
        logger.info("Before Validation - link: {}", link);
        Set<ConstraintViolation<Link>> violations = validator.validate(link);
        return handleViolations(violations, context);
    }

    private boolean validateRadioGroup(RadioGroup radioGroup, ConstraintValidatorContext context) {
        logger.info("Before Validation - radioGroup: {}", radioGroup);
        Set<ConstraintViolation<RadioGroup>> violations = validator.validate(radioGroup);
        return handleViolations(violations, context);
    }

    private boolean validateTable(Table table, ConstraintValidatorContext context) {
        logger.info("Before Validation - table: {}", table);
        Set<ConstraintViolation<Table>> violations = validator.validate(table);
        return handleViolations(violations, context);
    }

    private boolean validateTextBlock(TextBlock textBlock, ConstraintValidatorContext context) {
        logger.info("Before Validation - textBlock: {}", textBlock);
        Set<ConstraintViolation<TextBlock>> violations = validator.validate(textBlock);
        return handleViolations(violations, context);
    }

    private boolean handleViolations(Set<? extends ConstraintViolation<?>> violations, ConstraintValidatorContext context) {
        if (!violations.isEmpty()) {
            for (ConstraintViolation<?> violation : violations) {
                logger.error("Validation error: {} at {}", violation.getMessage(), violation.getPropertyPath());
                context.buildConstraintViolationWithTemplate(violation.getMessage())
                        .addPropertyNode("data")
                        .addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}