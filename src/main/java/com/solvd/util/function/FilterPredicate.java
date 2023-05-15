package com.solvd.util.function;

import java.util.function.Predicate;

/**
 * Functional interface for defining custom filtering logic.
 *
 * @param <T> the type of the object to be filtered
 */
@FunctionalInterface
public interface FilterPredicate<T> extends Predicate<T> {
    /**
     * Filters an object based on custom criteria.
     *
     * @param object the object to be filtered
     * @return true if the object passes the filter, false otherwise
     */
    boolean filter(T object);

    @Override
    default boolean test(T object) {
        return filter(object);
    }
}
