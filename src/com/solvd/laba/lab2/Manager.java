package com.solvd.laba.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Manager extends Employee implements DifferentDoings {
    public static final Logger logger = LogManager.getLogger(Manager.class.getName());

    protected ArrayList<Employee> employees;

    public Manager(String name, int age, String email) {
        super(name, age, email);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void doings() {
        logger.info("Managing employees...");
    }
}
