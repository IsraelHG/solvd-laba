package com.solvd.laba.lab2;

public class PaymentNotFoundException extends Exception {
    PaymentNotFoundException() {
    }

    PaymentNotFoundException(String message) {
        super(message);
    }

    PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
