package org.example.population;

import org.example.Cage;

import java.util.Random;

public class IslandObjectFactory {
    public static Animals createAnimals(AnimalsType animalsType) {
        return new Animals(animalsType);
    }

    public static Plants createPlants(double amount) {
        return new Plants(amount);
    }

    public static void population(Cage cage) {
        Random rnd = new Random();

        for (AnimalsType type : AnimalsType.values()) {
            int count = rnd.nextInt(15) + 1;
            for (int i = 0; i < count; i++) {
                cage.addAnimal(createAnimals(type));
            }
        }

        int plantsCount = rnd.nextInt(15) + 1;
        for (int i = 0; i < plantsCount; i++) {
            cage.addPlant(createPlants(10.0));
        }
    }
}
