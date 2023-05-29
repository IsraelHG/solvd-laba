package com.solvd.block1.lab2.exceptions;

public class PaymentNotFoundException extends Exception {
    public PaymentNotFoundException() {
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
