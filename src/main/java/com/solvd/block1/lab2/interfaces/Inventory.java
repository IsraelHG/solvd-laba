package com.solvd.block1.lab2.interfaces;
import com.solvd.block1.lab2.enums.Brand;
import com.solvd.block1.lab2.enums.Category;
import com.solvd.block1.lab2.enums.Color;
import com.solvd.block1.lab2.enums.Size;

public interface Inventory {
    void addProduct(String name, double price, int quantity, Category category);
    void addProduct(String name, double price, int quantity, Category category, Color color);

    void addProduct(String name, double price, int quantity, Category category, Color color, Size size);

    void addProduct(String name, double price, int quantity, Category category, Color color, Brand brand, Size size);

    void removeProduct(String name);
}
