package org.example.population;

import org.example.Cage;
import org.example.Island;

public abstract class Predators extends Animals{

    public Predators(String symbol, int maxPerCell, int speed, double satiety, double foodNeeded, int maxHunger) {
        super(symbol, maxPerCell, speed, satiety, foodNeeded, maxHunger);
    }

    @Override
    public void eat(Cage cage) {

    }

    @Override
    public void move(Cage cage, Island island) {

    }

    @Override
    public void multiply(Cage cage) {

    }
}
