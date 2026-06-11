package org.example;


import org.example.population.AllAnimals.*;
import org.example.population.Animals;

public class AnimalFactory {

    public static Animals createAnimal(AnimalsType type) {
        return new Animals(type);
    }
}
