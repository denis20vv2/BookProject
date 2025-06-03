package com.example.authorizationservice.table.validator;


import com.example.authorizationservice.cell.InterfaceElement.table.Column;

import com.example.authorizationservice.cell.InterfaceElement.filter.AvailableFilters;
import com.example.authorizationservice.user.web.UserController;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Component
public class TableValidator implements ConstraintValidator<ValidTable, Object> {

        private Validator validator;
        private static final Logger logger = LoggerFactory.getLogger(UserController.class);

        @Override
        public void initialize(ValidTable constraintAnnotation) {
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

                        if (item instanceof Column) {
                            return validateColumn((Column) object, context);
                        } else if (item instanceof Map) {

                            Map<?, ?> row = (Map<?, ?>) item;

                            for (Map.Entry<?, ?> entry : row.entrySet()) {
                                Object cellData = entry.getValue();

                                if (cellData instanceof Map<?, ?>) {
                                    Object typeObj = ((Map<?, ?>) cellData).get("type");

                                    if (!(typeObj instanceof String)) {
                                        context.buildConstraintViolationWithTemplate("Missing or invalid 'type' in cell: " + entry.getKey())
                                                .addPropertyNode("data[" + entry.getKey() + "]")
                                                .addConstraintViolation();
                                        return false;
                                    }

                                    String type = (String) typeObj;
                                    if (!type.equals("DROPDOWN") && !type.equals("INPUT") && !type.equals("CHECKBOX")) {
                                        context.buildConstraintViolationWithTemplate("Unsupported 'type': " + type + " in cell: " + entry.getKey())
                                                .addPropertyNode("data[" + entry.getKey() + "]")
                                                .addConstraintViolation();
                                        return false;
                                    }

                                } else {
                                    context.buildConstraintViolationWithTemplate("Each cell value must be a Map with a 'type' attribute")
                                            .addPropertyNode("data[" + entry.getKey() + "]")
                                            .addConstraintViolation();
                                    return false;
                                }
                            }
                        }
                        else return true;

                    }
                }
                return true;
            }

            if (object instanceof AvailableFilters) {
                AvailableFilters filterGroup = (AvailableFilters) object;
                return validateFilterGroup(filterGroup, context); ///
            } else {
                logger.error("Unsupported type: {}", object.getClass().getSimpleName());
                context.buildConstraintViolationWithTemplate("Invalid object type" + object.getClass())
                        .addPropertyNode("tableJson")
                        .addConstraintViolation();
                return false;
            }
        } else { // Пустые объекты скорее всего должны приходить. Уточнить!
            logger.error("TableJson is null for the object");
            context.buildConstraintViolationWithTemplate("TableJson cannot be null or empty" + object.getClass())
                    .addPropertyNode("TableJson")
                    .addConstraintViolation();
            return false;
        }
    }

    private boolean validateFilterGroup(AvailableFilters filterGroup, ConstraintValidatorContext context) {
        logger.info("Before Validation - filterGroup: {}", filterGroup);
        Set<ConstraintViolation<AvailableFilters>> violations = validator.validate(filterGroup);
        return handleViolations(violations, context);
    }

    private boolean validateColumn (Column column, ConstraintValidatorContext context){
        logger.info("Before Validation - column: {}", column);
        Set<ConstraintViolation<Column>> violations = validator.validate(column);
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
