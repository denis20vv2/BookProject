package com.example.storage.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientStockException extends RuntimeException {
    private final int existingQuantity;
    private final int requiredQuantity;
    public InsufficientStockException(String message, int existingQuantity, int requiredQuantity) {
        super(message);
        this.existingQuantity = existingQuantity;
        this.requiredQuantity = requiredQuantity;
    }
}
