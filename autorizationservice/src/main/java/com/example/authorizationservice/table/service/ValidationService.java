package com.example.authorizationservice.table.service;

import com.example.authorizationservice.cell.InterfaceElement.InterfaceElement.InterfaceElement;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationService {

    private final Validator validator;

    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    public void validateComponent(InterfaceElement component) {
        Set<ConstraintViolation<Object>> violations = validator.validate(component);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}