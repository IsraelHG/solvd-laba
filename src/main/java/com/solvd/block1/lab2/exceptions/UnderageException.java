package com.solvd.block1.lab2.exceptions;

public class UnderageException extends Exception {
    public UnderageException() {
    }

    public UnderageException(String message) {
        super(message);
    }

    public UnderageException(String message, Throwable cause) {
        super(message, cause);
    }
}
