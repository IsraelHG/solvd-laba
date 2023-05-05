package com.solvd.laba.lab2;

public class EmployeeNotFoundException extends Exception {
    EmployeeNotFoundException() {
    }

    EmployeeNotFoundException(String message) {
        super(message);
    }

    EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
