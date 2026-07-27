package org.example.revitalizationOfLife;

import org.example.Cage;
import org.example.Island;
import org.example.population.Animals;
import org.example.population.AnimalsType;
import org.example.population.Plants;

import java.util.*;

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


    private static final Map<AnimalsType, Map<AnimalsType, Double>> PREY_KEY = Map.of(
            AnimalsType.WOLF, Map.of(
                    AnimalsType.RABBIT, 0.25,
                    AnimalsType.DUCK, 0.25
            ),
            AnimalsType.BEAR, Map.of(
                    AnimalsType.RABBIT, 0.70,
                    AnimalsType.WOLF, 0.70,
                    AnimalsType.BOAR, 0.70,
                    AnimalsType.MOUSE, 0.70,
                    AnimalsType.DUCK, 0.70,
                    AnimalsType.ELK, 0.70
            )
    );


    public void eat(Animals animal, Cage cage) {
        AnimalsType predator = animal.getAnimalsType();

        if (PREY_KEY.containsKey(predator)) {
            Map<AnimalsType, Double> animalsTypes = PREY_KEY.get(predator);
            cage.getAnimals().stream()
                    .filter(a -> animalsTypes.containsKey(a.getAnimalsType()))
                    .findFirst()
                    .ifPresent(victim -> {
                        double predatorValue = animalsTypes.get(victim.getAnimalsType());
                        if (Math.random() < predatorValue) {
                            cage.removeAnimal(victim);
                        }
                    });
        } else {
            eatPlants(cage, PLANTS_KEY.get(predator));
        }
    }

    private void eatPlants(Cage cage, double chance) {
        if (!cage.getPlants().isEmpty() && Math.random() < chance) {
            Plants plant = cage.getPlants().get(0);
            cage.getPlants().remove(plant);
        }
    }

    private static final Map<AnimalsType, Double> PLANTS_KEY = Map.of(
            AnimalsType.RABBIT, 0.25,
            AnimalsType.BOAR, 0.50,
            AnimalsType.MOUSE, 0.10,
            AnimalsType.DUCK, 0.20,
            AnimalsType.ELK, 0.90
    );
}
