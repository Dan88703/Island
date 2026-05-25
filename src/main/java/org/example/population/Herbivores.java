package org.example.population;

import org.example.Cage;
import org.example.Island;
import org.example.population.AllAnimals.Rabbit;
import java.util.List;
import java.util.Random;

public abstract class Herbivores extends Animals{


    public Herbivores(String symbol,int maxPerCell, int speed) {
        super(symbol,maxPerCell, speed);
    }

    @Override
    public void eat(Cage cage) {
        Plants plants = cage.getPlant();
        if(plants != null && plants.getAmount() > 0 && new Random().nextInt(100) < 25){
            plants.consume(0.5);
        }
    }

    @Override
    public void move(Cage cage, Island island) {
        Random rnd = new Random();

        int x = rnd.nextInt(5);
        int y = rnd.nextInt(5);

        if(island.isAvailable(x, y)){
            Cage newCage = island.getCell(x, y);
            System.out.println(this.getSymbol() + " move from " + cage.getX() + ", " +  cage.getY() + " to " + newCage.getX() + ", " +  newCage.getY());

            cage.removeAnimal(this);
            newCage.addAnimal(this);
        }
    }

    @Override
    public void multiply(Cage cage) {
        List<Animals> rabbits = cage.getAnimals().stream()
                .filter(a -> a instanceof Rabbit)
                .toList();

        if(!rabbits.isEmpty() && new Random().nextInt(100) < 50 && rabbits.size() < this.maxPerCell){
            cage.addAnimal(new Rabbit());
        }
    }
}
