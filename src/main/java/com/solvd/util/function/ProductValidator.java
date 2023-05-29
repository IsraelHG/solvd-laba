package com.solvd.util.function;

import com.solvd.block1.lab2.Product;

/**
 * Functional interface for validating products.
 */
@FunctionalInterface
public interface ProductValidator {
    /**
     * Validates a product based on specific criteria.
     *
     * @param product the product to validate
     * @return true if the product passes the validation, false otherwise
     */
    boolean validate(Product product);
}