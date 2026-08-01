package org.example.population;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Plants {
    public Plants(double amount) {
        this.amount = amount;
    }

    private double amount;


    public void grow(int amount) {
        this.amount += amount;
    }

    public void consume(double eaten) {
        amount -= eaten;
    }

}
