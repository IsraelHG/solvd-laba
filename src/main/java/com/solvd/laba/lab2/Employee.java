package com.solvd.laba.lab2;

import com.solvd.laba.lab2.interfaces.Browsable;
import com.solvd.laba.lab2.interfaces.Restockable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee extends Person implements Browsable, Restockable {
    public static final Logger LOGGER = LogManager.getLogger(Employee.class.getName());
    
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
    public void browse() {
        LOGGER.info(this.name + " is browsing the store...");
    }

    @Override
    public void restock() {
        LOGGER.info(this.name + " is restocking shelves...");
    }
}
