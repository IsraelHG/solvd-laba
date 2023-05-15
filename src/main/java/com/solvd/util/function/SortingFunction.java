package com.solvd.util.function;

import java.util.List;

/**
 * Functional interface for sorting a list of elements.
 *
 * @param <T> the type of elements in the list
 */
@FunctionalInterface
public interface SortingFunction<T> {
    /**
     * Sorts a list of products and returns the sorted list.
     *
     * @param list the list of products to be sorted
     * @return the sorted list of products
     */
    List<T> sort(List<T> list);
}
