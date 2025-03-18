package com.example.authorizationservice.cell.validationСheck;

import com.example.authorizationservice.cell.InterfaceElement.Button.Action;
import com.example.authorizationservice.cell.InterfaceElement.Button.Style;
import com.example.authorizationservice.cell.InterfaceElement.Container.ContainerStyle;
import com.example.authorizationservice.cell.InterfaceElement.Frame.StyleFrame;
import com.example.authorizationservice.cell.InterfaceElement.Image.StyleImage;
import com.example.authorizationservice.cell.InterfaceElement.checkbox.StyleCheckbox;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.Option;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.StyleDropdown;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.ValidationDropdown;
import com.example.authorizationservice.cell.InterfaceElement.filters.*;
import com.example.authorizationservice.cell.InterfaceElement.form.Field;
import com.example.authorizationservice.cell.InterfaceElement.form.Submit;
import com.example.authorizationservice.cell.InterfaceElement.link.Icon;
import com.example.authorizationservice.cell.InterfaceElement.link.StyleLink;
import com.example.authorizationservice.cell.InterfaceElement.radioGroup.StyleRadio;
import com.example.authorizationservice.cell.InterfaceElement.table.Column;
import com.example.authorizationservice.cell.InterfaceElement.table.FilterTable;
import com.example.authorizationservice.cell.InterfaceElement.table.Pagination;
import com.example.authorizationservice.cell.InterfaceElement.table.Row;
import com.example.authorizationservice.cell.InterfaceElement.textBlock.StyleTextBlock;
import com.example.authorizationservice.user.web.UserController;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Component
public class NestedValidator implements ConstraintValidator<NestedValid, Object> {

    private Validator validator;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public void initialize(NestedValid constraintAnnotation) {
        if (this.validator == null) {
            this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object != null) {


            if (object instanceof ArrayList) {
                ArrayList<?> arrayList = (ArrayList<?>) object;

                for (Object item : arrayList) {
                    if (item != null) {
                        // Валидация для каждого типа объекта с использованием instanceof
                        if (object instanceof Option) {
                            return validateOption((Option) object, context);
                        } else if (object instanceof FilterData) {
                            return validateFilterData((FilterData) object, context);
                        }else if (object instanceof StyleDropdown) {
                            return validateStyleDropdown((StyleDropdown) object, context);
                        } else if (object instanceof Field) {
                            return validateField((Field) object, context);
                        } else if (object instanceof Column) {
                            return validateColumn((Column) object, context);
                        } else if (object instanceof Row) {
                            return validateRow((Row) object, context);
                        }else return true;

                    }
                }

            }

            // Проверка на конкретные типы объектов с использованием instanceof
            if (object instanceof Action) {
                Action action = (Action) object;
                return validateAction(action, context);
            } else if (object instanceof Style) {
                Style style = (Style) object;
                return validateStyle(style, context);
            } else if (object instanceof Submit) {
                Submit submit = (Submit) object;
                return validateSubmit(submit, context);
            } else if (object instanceof Value) {
                Value value = (Value) object;
                return validateValue(value, context);
            } else if (object instanceof Validation) {
                Validation validation = (Validation) object;
                return validateValidation(validation, context);
            } else if (object instanceof Icon) {
                Icon icon = (Icon) object;
                return validateIcon(icon, context);

            } else if (object instanceof StyleLink) {
                StyleLink styleLink = (StyleLink) object;
                return validateStylesLink(styleLink, context);

            } else if (object instanceof Pagination) {
                Pagination pagination = (Pagination) object;
                return validatePagination(pagination, context);

            }else if (object instanceof FilterTable) {
                FilterTable filterTable = (FilterTable) object;
                return validateFilterTable(filterTable, context);

            }else if (object instanceof StyleCheckbox) {
                StyleCheckbox styleCheckbox = (StyleCheckbox) object;
                return validateStyleCheckbox(styleCheckbox, context);

            }else if (object instanceof ValidationDropdown) {
                ValidationDropdown validationDropdown = (ValidationDropdown) object;
                return validateValidationDropdown(validationDropdown, context);

            }else if (object instanceof StyleRadio) {
                StyleRadio styleRadio = (StyleRadio) object;
                return validateStyleRadio(styleRadio, context);

            }else if (object instanceof ActionFilter) {
                ActionFilter actionFilter = (ActionFilter) object;
                return validateActionFilter(actionFilter, context);

            }else if (object instanceof ExistAction) {
                ExistAction existAction = (ExistAction) object;
                return validateExistAction(existAction, context);
            }else if (object instanceof StyleTextBlock) {
                StyleTextBlock styleTextBlock = (StyleTextBlock) object;
                return validateStyleTextBlock(styleTextBlock, context);
            }else if (object instanceof ContainerStyle) {
                ContainerStyle containerStyle = (ContainerStyle) object;
                return validateContainerStyle(containerStyle, context);
            }else if (object instanceof StyleImage) {
                StyleImage styleImage = (StyleImage) object;
                return validateStyleImage(styleImage, context);
            }else if (object instanceof StyleFrame) {
                StyleFrame styleFrame = (StyleFrame) object;
                return validateStyleFrame(styleFrame, context);
            }
            else {
                logger.error("Unsupported type: {}", object.getClass().getSimpleName());
                context.buildConstraintViolationWithTemplate("Invalid object type" + object.getClass())
                        .addPropertyNode("data")
                        .addConstraintViolation();
                return false; // Неверный тип
            }
        } else {
            logger.error("Data is null for the object");
            context.buildConstraintViolationWithTemplate("Data cannot be null or empty" + object.getClass())
                    .addPropertyNode("data")
                    .addConstraintViolation();
            return false;
        }
    }

    private boolean validateStyleCheckbox(StyleCheckbox styleCheckbox, ConstraintValidatorContext context) {
        logger.info("Before Validation - styleCheckbox: {}", styleCheckbox);
        Set<ConstraintViolation<StyleCheckbox>> violations = validator.validate(styleCheckbox);
        return handleViolations(violations, context);
    }

    private boolean validateStyleFrame( StyleFrame styleFrame, ConstraintValidatorContext context) {
        logger.info("Before Validation - styleFrame: {}", styleFrame);
        Set<ConstraintViolation<StyleFrame>> violations = validator.validate(styleFrame);
        return handleViolations(violations, context);
    }

    private boolean validateContainerStyle( ContainerStyle containerStyle, ConstraintValidatorContext context) {
        logger.info("Before Validation - containerStyle: {}", containerStyle);
        Set<ConstraintViolation<ContainerStyle>> violations = validator.validate(containerStyle);
        return handleViolations(violations, context);
    }

    private boolean validateStyleImage( StyleImage styleImage, ConstraintValidatorContext context) {
        logger.info("Before Validation - styleImage: {}", styleImage);
        Set<ConstraintViolation<StyleImage>> violations = validator.validate(styleImage);
        return handleViolations(violations, context);
    }

    private boolean validateAction(Action action, ConstraintValidatorContext context) {
        logger.info("Before Validation - action: {}", action);
        Set<ConstraintViolation<Action>> violations = validator.validate(action);
        return handleViolations(violations, context);
    }

    private boolean validateValidationDropdown(ValidationDropdown validationDropdown, ConstraintValidatorContext context) {
        logger.info("Before Validation - validationDropdown: {}", validationDropdown);
        Set<ConstraintViolation<ValidationDropdown>> violations = validator.validate(validationDropdown);
        return handleViolations(violations, context);
    }

    private boolean validateActionFilter(ActionFilter actionFilter, ConstraintValidatorContext context) {
        logger.info("Before Validation - actionFilter: {}", actionFilter);
        Set<ConstraintViolation<ActionFilter>> violations = validator.validate(actionFilter);
        return handleViolations(violations, context);
    }

    private boolean validateExistAction(ExistAction existAction, ConstraintValidatorContext context) {
        logger.info("Before Validation - actionFilter: {}", existAction);
        Set<ConstraintViolation<ExistAction>> violations = validator.validate(existAction);
        return handleViolations(violations, context);
    }

    private boolean validateStyleRadio(StyleRadio styleRadio, ConstraintValidatorContext context) {
        logger.info("Before Validation - styleRadio: {}", styleRadio);
        Set<ConstraintViolation<StyleRadio>> violations = validator.validate(styleRadio);
        return handleViolations(violations, context);
    }

    private boolean validateStyleTextBlock(StyleTextBlock styleTextBlock, ConstraintValidatorContext context) {
        logger.info("Before Validation - styleTextBlock: {}", styleTextBlock);
        Set<ConstraintViolation<StyleTextBlock>> violations = validator.validate(styleTextBlock);
        return handleViolations(violations, context);
    }

    private boolean validateFilterTable(FilterTable filterTable, ConstraintValidatorContext context) {
        logger.info("Before Validation - filterTable: {}", filterTable);
        Set<ConstraintViolation<FilterTable>> violations = validator.validate(filterTable);
        return handleViolations(violations, context);
    }

    private boolean validateFilter(Filter filter, ConstraintValidatorContext context) {
        logger.info("Before Validation - filter: {}", filter);
        Set<ConstraintViolation<Filter>> violations = validator.validate(filter);
        return handleViolations(violations, context);
    }

    private boolean validateStyle(Style style, ConstraintValidatorContext context) {
        logger.info("Before Validation - style: {}", style);
        Set<ConstraintViolation<Style>> violations = validator.validate(style);
        return handleViolations(violations, context);
    }

   /* private boolean validateOption(Option option, ConstraintValidatorContext context) {
        logger.info("Before Validation - option: {}", option);
        Set<ConstraintViolation<Option>> violations = validator.validate(option);
        return handleViolations(violations, context);
    }*/

   /* private boolean validateFilterData(FilterData filterData, ConstraintValidatorContext context) {
        logger.info("Before Validation - filterData: {}", filterData);
        Set<ConstraintViolation<FilterData>> violations = validator.validate(filterData);
        return handleViolations(violations, context);
    }*/

    private boolean validateSubmit(Submit submit, ConstraintValidatorContext context) {
        logger.info("Before Validation - submit: {}", submit);
        Set<ConstraintViolation<Submit>> violations = validator.validate(submit);
        return handleViolations(violations, context);
    }

    private boolean validateValue(Value value, ConstraintValidatorContext context) {
        logger.info("Before Validation - value: {}", value);
        Set<ConstraintViolation<Value>> violations = validator.validate(value);
        return handleViolations(violations, context);
    }

    /*private boolean validateField(Field field, ConstraintValidatorContext context) {
        logger.info("Before Validation - field: {}", field);
        Set<ConstraintViolation<Field>> violations = validator.validate(field);
        return handleViolations(violations, context);
    }*/

    private boolean validateValidation(Validation validation, ConstraintValidatorContext context) {
        logger.info("Before Validation - validation: {}", validation);
        Set<ConstraintViolation<Validation>> violations = validator.validate(validation);
        return handleViolations(violations, context);
    }

    private boolean validateIcon(Icon icon, ConstraintValidatorContext context) {
        logger.info("Before Validation - icon: {}", icon);
        Set<ConstraintViolation<Icon>> violations = validator.validate(icon);
        return handleViolations(violations, context);
    }

    private boolean validateStylesLink(StyleLink stylesLink, ConstraintValidatorContext context) {
        logger.info("Before Validation - stylesLink: {}", stylesLink);
        Set<ConstraintViolation<StyleLink>> violations = validator.validate(stylesLink);
        return handleViolations(violations, context);
    }

   /* private boolean validateColumn(Column column, ConstraintValidatorContext context) {
        logger.info("Before Validation - column: {}", column);
        Set<ConstraintViolation<Column>> violations = validator.validate(column);
        return handleViolations(violations, context);
    }*/

    private boolean validatePagination(Pagination pagination, ConstraintValidatorContext context) {
        logger.info("Before Validation - pagination: {}", pagination);
        Set<ConstraintViolation<Pagination>> violations = validator.validate(pagination);
        return handleViolations(violations, context);
    }

    /*private boolean validateRow(Row row, ConstraintValidatorContext context) {
        logger.info("Before Validation - row: {}", row);
        Set<ConstraintViolation<Row>> violations = validator.validate(row);
        return handleViolations(violations, context);
    }*/

    private boolean validateStyleDropdown (StyleDropdown styleDropdown, ConstraintValidatorContext context){
        logger.info("Before Validation - style: {}", styleDropdown);
        Set<ConstraintViolation<StyleDropdown>> violations = validator.validate(styleDropdown);
        return handleViolations(violations, context);
    }

    private boolean validateOption (Option option, ConstraintValidatorContext context){
        logger.info("Before Validation - option: {}", option);
        Set<ConstraintViolation<Option>> violations = validator.validate(option);
        return handleViolations(violations, context);
    }

    private boolean validateFilterData (FilterData filterData, ConstraintValidatorContext context){
        logger.info("Before Validation - filterData: {}", filterData);
        Set<ConstraintViolation<FilterData>> violations = validator.validate(filterData);
        return handleViolations(violations, context);
    }

    private boolean validateField (Field field, ConstraintValidatorContext context){
        logger.info("Before Validation - field: {}", field);
        Set<ConstraintViolation<Field>> violations = validator.validate(field);
        return handleViolations(violations, context);
    }

    private boolean validateColumn (Column column, ConstraintValidatorContext context){
        logger.info("Before Validation - column: {}", column);
        Set<ConstraintViolation<Column>> violations = validator.validate(column);
        return handleViolations(violations, context);
    }

    private boolean validateRow (Row row, ConstraintValidatorContext context){
        logger.info("Before Validation - row: {}", row);
        Set<ConstraintViolation<Row>> violations = validator.validate(row);
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