package com.solvd.laba.lab2.enums;

public enum ShippingMethod {
    STANDARD(5.99),
    EXPRESS(12.99),
    PRIME(0.00) {
        @Override
        public String getDescription() {
            return "Free 2-day shipping for Prime members.";
        }
    };

    private final double price;

    ShippingMethod(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return "Delivery within 5-7 business days.";
    }
}
