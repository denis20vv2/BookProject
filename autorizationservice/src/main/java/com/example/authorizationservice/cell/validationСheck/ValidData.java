package com.example.authorizationservice.cell.validationСheck;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidData {
   String message() default "Некорректные данные";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}


