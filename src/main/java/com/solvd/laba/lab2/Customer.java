package com.solvd.laba.lab2;

import com.solvd.laba.lab2.interfaces.Browsable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer extends Person implements Browsable {
    public static final Logger LOGGER = LogManager.getLogger(Customer.class.getName());

    public Customer(String name, int age) {
        super(name, age);
    }

    @Override
    public void browse() {
        LOGGER.info(this.name + " is browsing the store...");
    }

}