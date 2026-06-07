package org.example.population;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public abstract class Animals {
    protected final String id = UUID.randomUUID().toString();
    protected char rations;
    protected int foodLimit;
    protected String symbol;
    protected int speed;
    protected boolean alive = true;

    public Animals(String symbol, int foodLimit, char rations ,int speed) {
        this.foodLimit = foodLimit;
        this.rations = rations;
        this.speed = speed;
        this.symbol = symbol;
    }


}
