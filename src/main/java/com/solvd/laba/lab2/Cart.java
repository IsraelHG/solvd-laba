package com.solvd.laba.lab2;

import com.solvd.laba.lab2.exceptions.UnderageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public final class Cart {
    public static final Logger logger = LogManager.getLogger(Cart.class.getName());

    private final ArrayList<Product> cart;
    private final Customer customer;

    public Cart(Customer customer) {
        this.cart = new ArrayList<>();
        this.customer = customer;
    }

    public String getCustomer() {
        return this.customer.getName();
    }

    public void addToCart(String itemName, Store store, int quantity) {
        if (customer.getAge() < 21 && store.getProduct(itemName).getName().equals("alcohol")) {
            try {
                throw new UnderageException(customer.getName() + " is under 21. " + customer.getName() + " cannot buy Alcohol", new RuntimeException());
            } catch (UnderageException e) {
                logger.info(e.getMessage() + ", " + e.getCause());
                return;
            }
        }
        boolean found = false;
        for (Product product : cart) {
            if (product.getName().equals(itemName)) {
                product.setQuantity(product.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            Product productToCart = new Product(itemName, store.getProduct(itemName).getPrice(), quantity);
            cart.add(productToCart);
        }
    }

    public void removeFromCart(Product item) {
        this.cart.remove(item);
    }

    public void clear() {
        this.cart.clear();
    }

    public int size() {
        return this.cart.size();
    }

    public boolean isEmpty() {
        return this.cart.isEmpty();
    }

    public void printCart() {
        logger.info(cart);
    }

    public ArrayList<Product> getItems() {
        return new ArrayList<>(this.cart);
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Product item : this.cart) {
            total += item.getPrice() * item.getQuantity();
        }
        total = total * (1 + Main.TAX);
        return total;
    }
}
