package org.example.population;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public abstract class Animals implements LifeCycle{
    protected final String id = UUID.randomUUID().toString();
    protected int maxPerCell;
    protected String symbol;
    protected int speed;
    protected boolean alive = true;

    public Animals(String symbol,  int maxPerCell, int speed) {
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.symbol = symbol;
    }



}
