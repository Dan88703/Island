package org.example.revitalizationOfLife;

import org.example.Cage;
import org.example.Island;
import org.example.population.Animals;
import org.example.population.AnimalsType;
import org.example.population.Plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EatService {
    private final Island island;

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
        if (animal.getAnimalsType() == AnimalsType.WOLF) {
            eatAsWolf(cage);
        } else if (animal.getAnimalsType() == AnimalsType.BEAR) {
            eatAsBear(cage);
        } else if (animal.getAnimalsType() == AnimalsType.RABBIT) {
            eatPlants(cage, 0.25);
        } else if (animal.getAnimalsType() == AnimalsType.BOAR) {
            eatPlants(cage, 0.50);
        } else if (animal.getAnimalsType() == AnimalsType.MOUSE) {
            eatPlants(cage, 0.10);
        } else if (animal.getAnimalsType() == AnimalsType.DUCK) {
            eatPlants(cage, 0.20);
        } else if (animal.getAnimalsType() == AnimalsType.ELK) {
            eatPlants(cage, 0.90);
        }
    }

    private void eatAsWolf(Cage cage) {
        List<Animals> prey = cage.getAnimals()
                .stream()
                .filter(a -> a.getAnimalsType() == AnimalsType.RABBIT
                        || a.getAnimalsType() == AnimalsType.DUCK)
                .toList();
        if (!prey.isEmpty() && Math.random() < 0.25
        ) {
            Animals victim = prey.get(new Random().nextInt(prey.size()));
            cage.getAnimals().remove(victim);
        }
    }

    private void eatAsBear(Cage cage) {
        List<Animals> prey = cage.getAnimals()
                .stream()
                .filter(a -> a.getAnimalsType() != AnimalsType.BEAR)
                .toList();
        if (!prey.isEmpty() && Math.random() < 0.7) {
            Animals victim = prey.get(new Random().nextInt(prey.size()));
            cage.getAnimals().remove(victim);
        }
    }

    private void eatPlants(Cage cage, double chance) {
        if (!cage.getPlants().isEmpty() && Math.random() < chance) {
            Plants plant = cage.getPlants().get(0);
            cage.getPlants().remove(plant);
        }
    }
}
