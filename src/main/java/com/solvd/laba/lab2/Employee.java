package com.solvd.laba.lab2;

import com.solvd.laba.lab2.interfaces.DifferentDoings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee extends Person implements DifferentDoings {
    public static final Logger logger = LogManager.getLogger(Employee.class.getName());
    
    protected String email;

    public Employee(String name, int age, String email) {
        super(name, age);
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void doings() {
        logger.info("Restocking items...");
    }
}
