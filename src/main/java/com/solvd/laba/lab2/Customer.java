package com.solvd.laba.lab2;

import com.solvd.laba.lab2.interfaces.DifferentDoings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer extends Person implements DifferentDoings {
    public static final Logger logger = LogManager.getLogger(Customer.class.getName());

    public Customer(String name, int age) {
        super(name, age);
    }

    @Override
    public void doings() {
        logger.info("Browsing...");
    }

}