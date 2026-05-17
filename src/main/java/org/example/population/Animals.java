package org.example.population;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public abstract class Animals implements LifeCycle{
    protected final String id = UUID.randomUUID().toString();
    protected String symbol;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected int maxHunger;
    protected double satiety;
    protected boolean alive = true;

    public Animals(String symbol, int maxPerCell, int speed, double satiety, double foodNeeded, int maxHunger) {
        this.symbol = symbol;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.satiety = satiety;
        this.foodNeeded = foodNeeded;
        this.maxHunger = maxHunger;
    }



}
