package com.solvd.block1.lab2;

import com.solvd.block1.lab2.interfaces.Manageable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Manager extends Employee implements Manageable {
    public static final Logger LOGGER = LogManager.getLogger(Manager.class.getName());

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
    public void browse() {
        LOGGER.info(this.name + " is browsing the store...");
    }

    @Override
    public void restock() {
        LOGGER.info(this.name + " is restocking shelves...");
    }

    @Override
    public void manage() {
        LOGGER.info(this.name + " is managing employees...");
    }
}
