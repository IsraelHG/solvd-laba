package com.solvd.laba.lab2;

import com.solvd.laba.lab2.exceptions.EmployeeNotFoundException;
import com.solvd.laba.lab2.exceptions.PaymentNotFoundException;
import com.solvd.util.collections.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Store {
    public static final Logger logger = LogManager.getLogger(Store.class.getName());

    private String name;
    private String address;
    private Payment payment;
    private HashMap<String, Product> products;
    private final ArrayList<Account> customerAccounts;
    private final LinkedList<Employee> employeeAccounts;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.products = new HashMap<>();
        this.customerAccounts = new ArrayList<>();
        this.employeeAccounts = new LinkedList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public HashMap<String, Product> getProducts() {
        return this.products;
    }

    public void displayProducts() {
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            logger.info(product.getName() + " - (price: " + product.getPrice() + ") - (quantity: " + product.getQuantity() + ")");
        }
    }

    public void addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        products.put(name, product);
    }

    public void removeProduct(String name) {
        products.remove(name);
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public boolean hasProduct(String name) {
        return products.containsKey(name);
    }

    public int hasQuantity(String name) {
        Product product = products.get(name);
        return product.getQuantity();
    }

    //---------------------------------------------------
    public void register(Customer customer, String email, String password) {
        Account acc = new Account(customer, email, password);
        customerAccounts.add(acc);
    }

    public void printCustomers() {
        logger.info(customerAccounts.toString());
    }

    //---------------------------------------------------
    public void employeeClockIn(Employee employee) {
        employeeAccounts.add(employee);
    }

    public void employeeClockOut(Employee employee) {
        employeeAccounts.remove(employee);
    }

    public void printEmployees() {
        logger.info(employeeAccounts.toString());
    }
    //---------------------------------------------------

    public void getHelp(Customer customer, String key) {
        if (employeeAccounts.size() == 0) {
            try {
                throw new EmployeeNotFoundException("Sorry " + customer.getName()
                        + ", there are no employees available to help you today.", new RuntimeException());
            } catch (EmployeeNotFoundException enfe) {
                logger.info(enfe.getMessage() + ", " + enfe.getCause());
            }
        } else {
            logger.info("Hi " + customer.getName() +
                    ", my name is " + employeeAccounts.get(0).getName() +
                    ". There's " + products.get(key).getQuantity()
                    + " " + products.get(key).getName() + " available today.");
        }
    }


    public void clearCart(Cart cart) {
        cart.clear();
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void checkout(Cart cart) {
        if (payment == null) {
            try {
                throw new PaymentNotFoundException("Payment method has not been set.", new RuntimeException());
            } catch (PaymentNotFoundException pnfe) {
                logger.error(pnfe.getMessage() + ", " + pnfe.getCause());
            }
        } else {
            //double totalPrice = cart.getTotalPrice();
            boolean success = payment.processPayment();
            if (success) {
                cart.clear();
                logger.info("Thank you for your purchase!");
            } else {
                logger.error("Transaction failed. Please try again or use a different payment method.");
            }
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Store otherStore = (Store) obj;
        return Objects.equals(address, otherStore.address) &&
                Objects.equals(name, otherStore.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}