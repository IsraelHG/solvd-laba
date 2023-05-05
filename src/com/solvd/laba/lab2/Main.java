package com.solvd.laba.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final double TAX = 0.0825;
    public static final Logger logger = LogManager.getLogger(Main.class.getName());

    static {
        logger.info("Creating the online shop.");
    }

    public static void main(String[] args) {

        Store store = new Store("store-mart", "123 Main Street");
        Customer customerIsrael = new Customer("Israel", 19);
        Customer customerJacob = new Customer("Jacob", 22);
        Employee employeeJohn = new Employee("John", 24, "john@john.com");
        Manager managerLester = new Manager("Lester", 40, "lester@lester.com");

        store.register(customerIsrael, "israel@israel.com", "password123");
        store.register(customerJacob, "jacob@jacob.com", "password456");
        store.printCustomers();

        store.employeeClockIn(employeeJohn);

        store.addProduct("milk", 3.0, 10);
        store.addProduct("eggs", 5.0, 7);
        store.addProduct("cheese", 6.0, 30);
        store.addProduct("ham", 5.5, 25);
        store.addProduct("alcohol", 20.0, 4);

        customerIsrael.doings();
        employeeJohn.doings();
        managerLester.doings();
        store.getHelp(customerIsrael, "milk");

        Cart cart = new Cart(customerIsrael);
        cart.addToCart("milk", store, 2);
        cart.addToCart("milk", store, 3);
        cart.addToCart("eggs", store, 2);
        cart.addToCart("ham", store, 1);
        cart.addToCart("alcohol", store, 2);
        cart.printCart();

        CreditCard creditCard = new CreditCard(cart.getTotalPrice(), "123456789012", "05/24", 123, 100);
        store.setPayment(creditCard);
        store.checkout(cart);

        store.employeeClockOut(employeeJohn);
    }

}