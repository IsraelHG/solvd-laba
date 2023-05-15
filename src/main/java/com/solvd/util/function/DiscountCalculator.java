package com.solvd.util.function;

/**
 * Functional interface representing a discount calculator for products.
 *
 * <p>The DiscountCalculator interface defines a contract for calculating discounts on products.
 * Implementations of this interface provide a mechanism to calculate discounts based on product attributes,
 * pricing rules, or other criteria. The discount calculation is performed on a single product and returns
 * the calculated discount as a double value.
 *
 * @param <T> the type of the item for which the discount is calculated
 */
@FunctionalInterface
public interface DiscountCalculator<T> {

    /**
     * Calculates the discount for the given item.
     *
     * @param item the item for which the discount is calculated
     * @return the calculated discount as a double value
     */
    double calculateDiscount(T item, double discountRate);
}
