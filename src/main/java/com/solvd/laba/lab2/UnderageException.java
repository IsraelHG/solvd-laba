package com.solvd.laba.lab2;

public class UnderageException extends Exception {
    UnderageException() {
    }

    UnderageException(String message) {
        super(message);
    }

    UnderageException(String message, Throwable cause) {
        super(message, cause);
    }
}
