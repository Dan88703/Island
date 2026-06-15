package org.example;


import org.example.population.Animals;
import org.example.population.AnimalsType;

public class AnimalFactory {

    public static Animals createAnimal(AnimalsType type) {
        return new Animals(type);
    }
}
