package com.solvd.laba.lab2;

import com.solvd.laba.lab2.exceptions.InsufficientFundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditCard extends Payment {
    public static final Logger LOGGER = LogManager.getLogger(CreditCard.class.getName());

    private String cardNumber;
    private String expirationDate;
    private int cvv;
    private double balance;

    public CreditCard(double amount, String cardNumber, String expirationDate, int cvv, double balance) {
        super(amount);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean processPayment() {
        //
        if (balance < getAmount()) {
            try {
                throw new InsufficientFundsException("Insufficient funds", new RuntimeException());
            } catch (InsufficientFundsException e) {
                LOGGER.error(e.getMessage() + ", " + e.getCause());
            }
            return false;
        }
        LOGGER.info("Processing credit card payment...");
        balance -= getAmount();
        LOGGER.info("Remaining balance: " + balance);
        return true;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", balance=" + balance +
                '}';
    }
}
