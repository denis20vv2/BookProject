package com.example.books.error;

public class Error {

    String message;


    public Error(String message) {
        this.message = message;
    }

    public String getError() {
        return message;
    }

    public void setError(String message) {
        this.message = message;
    }

}
