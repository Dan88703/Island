package org.example;

import org.example.population.Animals;
import org.example.population.Rabbit;
import org.example.population.Wolf;

public class AnimalFactory {
    public static Animals createAnimal(String type){
        return switch (type){
            case "WOLF" -> new Wolf();
            case "RABBIT" -> new Rabbit();
            default -> throw new IllegalArgumentException("Invalid animal type" + type);
        };
    }
}
