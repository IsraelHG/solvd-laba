package com.solvd.laba.lab2;

public class InsufficientFundsException extends Exception {
    InsufficientFundsException() {
    }

    InsufficientFundsException(String message) {
        super(message);
    }

    InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
