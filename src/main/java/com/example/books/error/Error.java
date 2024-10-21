package com.example.books.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
//@AllArgsConstructor
public class Error {

    public Error(LocalDateTime timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        //this.message = message;
    }

    private LocalDateTime timestamp;
    private int status;
    private String error;
    // private String message;
    private String path;
    private Map<String, String> fieldErrors;

}
