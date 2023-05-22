package com.solvd.laba.lab2;

import com.solvd.laba.lab2.enums.Brand;
import com.solvd.laba.lab2.enums.Category;
import com.solvd.laba.lab2.enums.Color;
import com.solvd.laba.lab2.enums.Size;

public class Product {
    private String name;
    private int id;
    private double price;
    private int quantity;
    Category category;
    Color color;
    Brand brand;
    Size size;

    public Product(String name, int id, double price, int quantity, Category category) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(String name, int id, double price, int quantity, Category category, Color color) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.color = color;
    }

    public Product(String name, int id, double price, int quantity, Category category, Color color, Brand brand, Size size) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.color = color;
        this.brand = brand;
        this.size = size;
    }

    public Product(String name, int id, double price, int quantity, Category category, Color color, Size size) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.color = color;
        this.size = size;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                ", color= " + color +
                ", brand=" + brand +
                ", size=" + size +
                '}';
    }
}