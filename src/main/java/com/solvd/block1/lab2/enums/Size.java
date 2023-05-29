package com.solvd.block1.lab2.enums;

public enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L");

    private final String abbreviation;

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
