package org.example;


import org.example.population.AllAnimals.*;
import org.example.population.Animals;
import org.example.population.Animals.*;

public class AnimalFactory {

    public static Animals createAnimal(String type) {
        return switch (type) {
            case "WOLF"   -> new Wolf();
            case "BEAR"   -> new Bear();
            case "RABBIT" -> new Rabbit();
            case "BOAR"   -> new WildBoar();
            case "MOUSE"  -> new Mouse();
            case "DUCK"   -> new Duck();
            case "ELK"    -> new Elk();
            default -> throw new IllegalArgumentException(type);
        };
    }
}
