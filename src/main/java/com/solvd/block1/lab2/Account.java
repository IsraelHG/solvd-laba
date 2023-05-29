package com.solvd.block1.lab2;

public final class Account {
    private Customer user;
    private String email;
    private String password;
    private CreditCard creditCard;
    private DebitCard debitCard;
    private double balance;

    public Account(Customer customer, String email, String password) {
        this.user = customer;
        this.email = email;
        this.password = password;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return user.getName() + ", " +
                user.getAge() + ", " + getEmail();
    }
}