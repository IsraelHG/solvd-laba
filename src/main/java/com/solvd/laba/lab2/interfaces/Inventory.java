package com.solvd.laba.lab2.interfaces;

public interface Inventory {
    void addProduct(String name, double price, int quantity);
    void removeProduct(String name);
}
