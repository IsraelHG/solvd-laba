package com.solvd.laba.lab2;

import com.solvd.laba.lab2.enums.Category;
import com.solvd.util.function.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;


public class Main {
    public static final double TAX = 0.0825;
    public static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    static {
        LOGGER.info("Creating the online shop.");
    }

    public static void main(String[] args) {

        Store store = new Store("store-mart", "123 Main Street");
        Customer customerIsrael = new Customer("Israel", 19);
        Customer customerJacob = new Customer("Jacob", 22);
        Employee employeeJohn = new Employee("John", 24, "john@john.com");
        Manager managerLester = new Manager("Lester", 40, "lester@lester.com");

        store.register(customerIsrael, "israel@israel.com", "password123");
        store.register(customerJacob, "jacob@jacob.com", "password456");

        store.employeeClockIn(employeeJohn);

        store.addProduct("milk", 3.0, 10, Category.FOODS);
        store.addProduct("eggs", 5.0, 7, Category.FOODS);
        store.addProduct("cheese", 6.0, 30, Category.FOODS);
        store.addProduct("ham", 6.0, 25, Category.FOODS);
        store.addProduct("alcohol", 20.0, 4, Category.FOODS);
        store.addProduct("Laptop", 700.0, 6, Category.ELECTRONICS);
        store.addProduct("iPhone 13", 730.0, 10, Category.ELECTRONICS);
        store.addProduct("The Great Gatsby", 30.0, 8, Category.BOOKS);
        store.addProduct("Red Plain T-Shirt", 20.0, 20, Category.CLOTHING);

        // Example usage of Function to increase product price by 10%
        store.updateProductPrice("milk", price -> price * 1.1);
        // Example usage of Function to decrease product price by 20%
        store.updateProductPrice("eggs", price -> price * 0.8);

        customerIsrael.browse();
        employeeJohn.restock();
        managerLester.manage();
        store.getHelp(customerIsrael, "milk");

        Cart cart = new Cart(customerIsrael);
        cart.addToCart("milk", store, 2);
        cart.addToCart("milk", store, 3);
        cart.addToCart("eggs", store, 2);
        cart.addToCart("ham", store, 1);
        cart.addToCart("Laptop", store,1);
        cart.addToCart("alcohol", store, 2);

        Cart filteredCart = cart.filter(product -> product.getPrice() >= 5);
        filteredCart.printCart();
        filteredCart.clear();

        // Call the addDiscounts() method with the lambda expression as a method argument
        // 1. Custom functional interface using lambda expression.
        store.addDiscounts(store, "Laptop", 0.1, (product, rate) -> {
            double discount = 0.0;
            // Custom discount calculation logic based on product attributes and discount rate
            if (product.getPrice() > 100.0) {
                discount = rate * product.getPrice();
            }
            return discount;
        });

        // Call the store.filterProducts() method with the lambda expression as a method argument
        // 2. Custom functional interface using lambda expression.
        List<Product> filteredProducts = store.filterProducts((product) ->
                product.getPrice() > 100.0 && product.getQuantity() > 0
        );

        // Call the store.validateProducts() method with the lambda expression as a method argument
        // 3. Custom functional interface using lambda expression.
        ProductValidator priceRangeValidator = (product) -> product.getPrice() >= 5.0 && product.getPrice() <= 6.0;
        store.validateProducts(priceRangeValidator);

        // Call the store.sortProducts() method with the lambda expression as a method argument
        // 4. Custom functional interface using lambda expression.
        List<Product> sortedProducts = store.sortProducts((list) -> {
            list.sort(Comparator.comparing(Product::getName));
            return list;
        });

        cart.printCart();

        double totalPrice = cart.calculateTotalPrice(Double::sum);
        LOGGER.info("Total Price (with tax): $" + totalPrice);

        CreditCard creditCard = new CreditCard(cart.getTotalPrice(), "123456789012", "05/24", 123, 1000);
        store.setPayment(creditCard);
        store.checkout(cart);

        // lambda functions from the function class
        store.sendNotification("israel@israel.com", (customerEmail) -> {
            String message = "Your order of: \n" + cart + "has been ordered!";
            LOGGER.info("Sending notification to " + customerEmail +
                    ": \n---------------------\n" + message);
        });
        store.clearCart(cart);

        store.displayProducts();
        store.displayProducts(Comparator.comparing(Product::getPrice));
        store.employeeClockOut(employeeJohn);
    }

}