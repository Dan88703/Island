package org.example.revitalizationOfLife;

import org.example.Cage;
import org.example.Island;
import org.example.population.AllAnimals.*;
import org.example.population.Animals;
import org.example.population.Plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EatService {
    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public EatService(Island island) {
        this.island = island;
    }

    public void process() {
        for (Cage cage : island.getCages()) {
            new ArrayList<>(cage.getAnimals())
                    .forEach(animal -> eat(animal, cage));
        }
    }


    public void eat(Animals animal, Cage cage) {

        if (animal instanceof Wolf) {
            eatAsWolf(cage);
        } else if (animal instanceof Bear) {
            eatAsBear(cage);
        } else {
            eatPlants(cage);
        }

    }

    private void eatAsWolf(Cage cage) {
        List<Animals> prey = cage.getAnimals()
                .stream()
                .filter(a -> !a.getClass().getSimpleName().equals("Bear") && !a.getClass().getSimpleName().equals("Wolf"))
                .toList();
        if (!prey.isEmpty() && Math.random() < 0.5) {
            Animals victim = prey.get(new Random().nextInt(prey.size()));
            cage.getAnimals().remove(victim);
        }
    }

    private void eatAsBear(Cage cage) {
        List<Animals> prey = cage.getAnimals()
                .stream()
                .filter(a -> a.getClass().getSimpleName().equals("Bear"))
                .toList();
        if (!prey.isEmpty() && Math.random() < 0.5) {
            Animals victim = prey.get(new Random().nextInt(prey.size()));
            cage.getAnimals().remove(victim);
        }
    }

    private void eatPlants(Cage cage) {
        if (!cage.getPlants().isEmpty() && Math.random() < 0.50) {
            Plants plant = cage.getPlants().get(0);
            cage.getPlants().remove(plant);
        }
    }
}
