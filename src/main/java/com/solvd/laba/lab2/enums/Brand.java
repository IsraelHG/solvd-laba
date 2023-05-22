package com.solvd.laba.lab2.enums;

public enum Brand {
    SAMSUNG("Samsung"),
    APPLE("Apple");

    private final String name;

    Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
