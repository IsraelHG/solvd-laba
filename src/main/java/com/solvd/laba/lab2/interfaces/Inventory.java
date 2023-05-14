package com.solvd.laba.lab2.interfaces;
import com.solvd.laba.lab2.enums.Category;

public interface Inventory {
    void addProduct(String name, double price, int quantity, Category category);
    void removeProduct(String name);
}
