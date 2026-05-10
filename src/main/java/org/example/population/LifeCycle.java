package org.example.population;

import org.example.Cage;
import org.example.Island;

public interface LifeCycle {

    public void  eat(Cage cage);
    public void move(Cage cage, Island island);
    public void multiply(Cage cage);

}
