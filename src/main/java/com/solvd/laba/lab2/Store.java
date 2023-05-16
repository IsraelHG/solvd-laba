package com.solvd.laba.lab2;

import com.solvd.laba.lab2.enums.Category;
import com.solvd.laba.lab2.exceptions.EmployeeNotFoundException;
import com.solvd.laba.lab2.exceptions.PaymentNotFoundException;
import com.solvd.laba.lab2.interfaces.Inventory;
import com.solvd.util.collections.LinkedList;
import com.solvd.util.function.DiscountCalculator;
import com.solvd.util.function.FilterPredicate;
import com.solvd.util.function.ProductValidator;
import com.solvd.util.function.SortingFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

/**
 * The Store class represents an online store.
 */
public class Store implements Inventory {
    public static final Logger LOGGER = LogManager.getLogger(Store.class.getName());

    private String name;
    private String address;
    private Payment payment;
    private HashMap<String, Product> products;
    private final ArrayList<Account> customerAccounts;
    private final LinkedList<Employee> employeeAccounts;
    private IntSupplier idSupplier;
    private final Set<Integer> generatedIds;
    private int maxId;
    private int nextId;

    /**
     * Constructs a new Store object.
     * Initializes the store name, and address, along with generatedIds set, maximum ID value, and next ID to be generated.
     * Configures the ID supplier as a lambda expression to generate unique IDs.
     */
    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.products = new HashMap<>();
        this.customerAccounts = new ArrayList<>();
        this.employeeAccounts = new LinkedList<>();

        generatedIds = new HashSet<>();
        maxId = 999; // Maximum ID value
        nextId = 0;
        // Lambda expression for the IntSupplier functional interface.
        idSupplier = () -> {
            if (nextId > maxId) {
                throw new IllegalStateException("No more unique IDs available.");
            }
            while (generatedIds.contains(nextId)) {
                nextId++;
            }
            generatedIds.add(nextId);
            return nextId;
        };
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public void setIdSupplier(IntSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }

    public IntSupplier getIdSupplier() {
        return idSupplier;
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

    /**
     * Displays all the products in the store.
     * The products are displayed in the order they are stored internally, without any specific sorting.
     */
    public void displayProducts() {
        products.values().forEach(LOGGER::info);
    }

    /**
     * Displays all the products in the store, sorted based on the provided comparator.
     * The products are sorted using the specified comparator before being displayed.
     *
     * @param comparator The comparator used to sort the products.
     *                   It defines the sorting order for the products.
     */
    public void displayProducts(Comparator<Product> comparator) {
        List<Product> sortedProducts = new ArrayList<>(products.values());
        sortedProducts.sort(comparator);
        sortedProducts.forEach(LOGGER::info);
    }


    @Override
    public void addProduct(String name, double price, int quantity, Category category) {
        //Generates unique ID's using the IntSupplier functional interface.
        int id = idSupplier.getAsInt();
        Product product = new Product(name, id, price, quantity, category);
        products.put(name, product);
    }

    /**
     * Updates the price of a product using the provided price updater function.
     *
     * @param productName  The name of the product.
     * @param priceUpdater The function that takes the current price as input and returns the updated price.
     */
    public void updateProductPrice(String productName, Function<Double, Double> priceUpdater) {
        Product product = getProduct(productName);
        double currentPrice = product.getPrice();
        double updatedPrice = priceUpdater.apply(currentPrice);
        product.setPrice(updatedPrice);
        LOGGER.info("Updated price for " + productName + ": $" + updatedPrice);
    }

    @Override
    public void removeProduct(String name) {
        products.remove(name);
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    /**
     * Retrieves all products in the store that belong to the specified category.
     *
     * @param category the category to filter the products by
     * @return a list of products belonging to the specified category
     */
    public List<Product> getProductsByCategory(Category category) {
        return products.values()
                .stream()
                .filter(product -> product.getCategory() == category)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all products in the store that have a price within the specified range.
     *
     * @param minPrice the minimum price of the range (inclusive)
     * @param maxPrice the maximum price of the range (inclusive)
     * @return a list of products within the specified price range
     */
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return products.values()
                .stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the total stock quantity of products in the store for the specified category.
     *
     * @param category the category to calculate the total stock for
     * @return the total stock quantity of products in the specified category
     */
    public int getTotalStockByCategory(Category category) {
        return products.values()
                .stream()
                .filter(product -> product.getCategory() == category)
                .mapToInt(Product::getQuantity)
                .sum();
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
        LOGGER.info(customerAccounts.toString());
    }

    //---------------------------------------------------
    public void employeeClockIn(Employee employee) {
        employeeAccounts.add(employee);
    }

    public void employeeClockOut(Employee employee) {
        employeeAccounts.remove(employee);
    }

    public void printEmployees() {
        LOGGER.info(employeeAccounts.toString());
    }
    //---------------------------------------------------

    public void getHelp(Customer customer, String key) {
        if (employeeAccounts.size() == 0) {
            try {
                throw new EmployeeNotFoundException("Sorry " + customer.getName()
                        + ", there are no employees available to help you today.", new RuntimeException());
            } catch (EmployeeNotFoundException enfe) {
                LOGGER.info(enfe.getMessage() + ", " + enfe.getCause());
            }
        } else {
            LOGGER.info("Hi " + customer.getName() +
                    ", my name is " + employeeAccounts.get(0).getName() +
                    ". There's " + products.get(key).getQuantity()
                    + " " + products.get(key).getName() + " available today.");
        }
    }

    /**
     * Applies a discount to a specific product in the store.
     *
     * @param store the store object
     * @param productName the name of the product to apply the discount to
     * @param discountRate the rate of the discount
     * @param discountCalculator the discount calculator lambda function
     */
    public void addDiscounts(Store store, String productName, double discountRate, DiscountCalculator<Product> discountCalculator) {
        Product discountedProduct = store.getProduct(productName);

        // Calculate the discount using the discount calculator lambda function
        double discount = discountCalculator.calculateDiscount(discountedProduct, discountRate);

        // Update the price with the discounted price
        discountedProduct.setPrice(discountedProduct.getPrice() - discount);

        // Print the details of the product and the discount
        LOGGER.info("Product: " + discountedProduct.getName());
        LOGGER.info("Discount: $" + discount);
        LOGGER.info("Final Price: $" + (discountedProduct.getPrice()));
    }

    /**
     * Filters the products in the store based on the provided filtering predicate.
     *
     * @param filterPredicate the predicate that defines the filtering logic
     * @return a list of products that satisfy the filtering condition
     */
    public List<Product> filterProducts(FilterPredicate<Product> filterPredicate) {
        List<Product> filteredProducts = new ArrayList<>();
        // Iterate over the products and apply the filter predicate
        for (Product product : products.values()) {
            if (filterPredicate.test(product)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    /**
     * Validates the products in the store based on the specified product validator.
     *
     * @param validator The product validator that defines the validation criteria.
     */
    public void validateProducts(ProductValidator validator) {
        for (Product product : products.values()) {
            if (validator.validate(product)) {
                LOGGER.info(product);
            }
        }
    }

    /**
     * Sorts the products in the store based on the specified sorting function.
     *
     * @param sortingFunction The sorting function that defines the sorting logic.
     * @return The sorted list of products.
     */
    public List<Product> sortProducts(SortingFunction<Product> sortingFunction) {
        List<Product> productList = new ArrayList<>(products.values());
        sortingFunction.sort(productList);
        return productList;
    }

    /**
     * Sends a notification to the customer with the specified email.
     *
     * @param email           The email of the customer to send the notification to.
     * @param notificationSender The functional interface implementation responsible for sending the notification.
     */
    public void sendNotification(String email, Consumer<String> notificationSender) {
        for (Account acc : customerAccounts) {
            if (acc.getEmail().equals(email)) {
                notificationSender.accept(acc.getEmail());
                return;
            }
        }
        LOGGER.error("Customer not found with email: " + email);
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
                LOGGER.error(pnfe.getMessage() + ", " + pnfe.getCause());
            }
        } else {
            //double totalPrice = cart.getTotalPrice();
            boolean success = payment.processPayment();
            if (success) {
                LOGGER.info("Thank you for your purchase!");
            } else {
                LOGGER.error("Transaction failed. Please try again or use a different payment method.");
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