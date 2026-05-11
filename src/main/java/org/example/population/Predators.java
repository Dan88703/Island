package org.example.population;

import org.example.Cage;
import org.example.Island;

import java.util.List;
import java.util.Random;

public abstract class Predators extends Animals{

    public Predators(String symbol, int maxPerCell, int speed, double satiety, double foodNeeded, int maxHunger) {
        super(symbol, maxPerCell, speed, satiety, foodNeeded, maxHunger);
    }

    @Override
    public void eat(Cage cage) {
        List<Animals> rabbits = cage.getAnimals()
                .stream()
                .filter(a -> a instanceof Rabbit).toList();

        if(!rabbits.isEmpty() && new Random().nextInt(100) < 25){
            Animals eatRabbit =  rabbits.get(new Random().nextInt(rabbits.size()));
            eatRabbit.setAlive(false);
            cage.removeAnimal(eatRabbit);
        }
    }

    @Override
    public void move(Cage cage, Island island) {
        Random rnd = new Random();

        int x = rnd.nextInt(5);
        int y = rnd.nextInt(5);

        if(island.isAvailable(x, y)){
            Cage newCage = island.getCell(x, y);
            System.out.println(this.getSymbol() + " move from " + cage.getX() + ", " +  cage.getY() + " to " + newCage.getX() + ", " + newCage.getY());
            cage.removeAnimal(this);
            newCage.addAnimal(this);
        }




    }

    @Override
    public void multiply(Cage cage) {

    }
}
