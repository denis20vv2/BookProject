package com.example.books.error;

import jakarta.persistence.EntityNotFoundException;
//import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<NotFoundExeptionResponse> handleNotFoundException(EntityNotFoundException exception, WebRequest request) {


        NotFoundExeptionResponse notFoundExeptionResponse = new NotFoundExeptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Entity Not Found",
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(notFoundExeptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<NotFoundExeptionResponse> handleExceptionNotParametersException(HttpMessageNotReadableException exception, WebRequest request) {

         NotFoundExeptionResponse notFoundExeptionResponse = new NotFoundExeptionResponse(
                 LocalDateTime.now(),
                 HttpStatus.BAD_REQUEST.value(),
                 "BAD REQUEST",
                 exception.getMessage(),
                 request.getDescription(false)
         );

         return new ResponseEntity<>(notFoundExeptionResponse, HttpStatus.BAD_REQUEST);
    }

}
