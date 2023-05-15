package com.solvd.laba.lab2;

import com.solvd.laba.lab2.exceptions.UnderageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.function.BinaryOperator;

/**
 * Represents a shopping cart that contains a list of products.
 */
public final class Cart {
    public static final Logger LOGGER = LogManager.getLogger(Cart.class.getName());

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
                LOGGER.info(e.getMessage() + ", " + e.getCause());
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
            Product productToCart = new Product(itemName, store.getProduct(itemName).getId(),
                    store.getProduct(itemName).getPrice(), quantity,
                    store.getProduct(itemName).getCategory());
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
        LOGGER.info("Printing " + customer.getName() + "'s cart: \n"+ cart);
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

    /**
     * Calculates the total price of products in the cart using the provided binary operator.
     *
     * @param totalPriceCalculator The binary operator that defines the operation to be performed on the accumulated total and product prices.
     * @return The total price of products in the cart.
     */
    public double calculateTotalPrice(BinaryOperator<Double> totalPriceCalculator) {
        double total = 0.0;
        for (Product product : cart) {
            total = totalPriceCalculator.apply(total, product.getPrice() * product.getQuantity());
        }
        return total * (1 + Main.TAX);
    }

    @Override
    public String toString() {
        StringBuilder cartItems = new StringBuilder();
        int total = 0;

        if (cart.isEmpty()) {
            return cartItems + "empty";
        }

        for (Product product : cart) {
            cartItems.append("Product: ").append(product.getName())
                    .append(", ID: ").append(product.getId())
                    .append(", Price: $").append(product.getPrice())
                    .append(", Quantity: ").append(product.getQuantity())
                    .append("\n");
            total += product.getPrice() * product.getQuantity();
        }
        cartItems.append("----------------------\n");
        cartItems.append("Subtotal: $").append((double)total).append("\n");
        cartItems.append("Total: $").append(total * (1 + Main.TAX)).append("\n");

        return cartItems.toString();
    }
}
