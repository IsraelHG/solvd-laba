package com.solvd.laba.lab2;

import com.solvd.laba.lab2.enums.Category;
import com.solvd.laba.lab2.enums.Size;
import com.solvd.util.function.DiscountCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.lab2.enums.Category;

import java.util.function.Predicate;

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

        store.addProduct("milk", 3.0, 10, Category.FOODS);
        store.addProduct("eggs", 5.0, 7, Category.FOODS);
        store.addProduct("cheese", 6.0, 30, Category.FOODS);
        store.addProduct("ham", 5.5, 25, Category.FOODS);
        store.addProduct("alcohol", 20.0, 4, Category.FOODS);
        store.addProduct("Laptop", 699.99, 6, Category.ELECTRONICS);
        store.addProduct("iPhone 13", 729.99, 10, Category.ELECTRONICS);
        store.addProduct("The Great Gatsby", 29.99, 8, Category.BOOKS);
        store.addProduct("Red Plain T-Shirt", 19.99, 20, Category.CLOTHING);

        customerIsrael.browse();
        employeeJohn.restock();
        managerLester.manage();
        store.getHelp(customerIsrael, "milk");

        Cart cart = new Cart(customerIsrael);
        cart.addToCart("milk", store, 2);
        cart.addToCart("milk", store, 3);
        cart.addToCart("eggs", store, 2);
        cart.addToCart("ham", store, 1);
        cart.addToCart("alcohol", store, 2);
        cart.addToCart("The Great Gatsby", store, 1);
        cart.addToCart("iPhone 13", store, 1);
        cart.printCart();


        Predicate<Product> isExpensive = product -> product.getPrice() > 100;

        System.out.println("\nExpensive Items in the cart: ");
        cart.getItems()
                .stream()
                .filter(isExpensive)
                .forEach(product -> System.out.println(product.getName()));

        CreditCard creditCard = new CreditCard(cart.getTotalPrice(), "123456789012", "05/24", 123, 1000);
        store.setPayment(creditCard);
        store.checkout(cart);

        store.employeeClockOut(employeeJohn);
    }

}