package com.solvd.laba.lab2;

public class InsufficientProductException extends Exception {
    InsufficientProductException() {
    }

    InsufficientProductException(String message) {
        super(message);
    }

    InsufficientProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
