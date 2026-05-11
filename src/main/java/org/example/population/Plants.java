package org.example.population;

public class Plants {
    public Plants(double amount) {
        this.amount = amount;
    }

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void consume(double eaten) {
        amount -= eaten;
    }

}
