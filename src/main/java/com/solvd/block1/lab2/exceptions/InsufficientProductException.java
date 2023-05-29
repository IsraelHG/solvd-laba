package com.solvd.block1.lab2.exceptions;

public class InsufficientProductException extends Exception {
    public InsufficientProductException() {
    }

    public InsufficientProductException(String message) {
        super(message);
    }

    public InsufficientProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
