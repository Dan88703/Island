package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.islandManager.Coordinate;
import org.example.population.Animals;
import org.example.population.Plants;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Getter
@Setter

public class Cage {

    private final List<Animals> animals = new CopyOnWriteArrayList<>();
    private final List<Plants> plants = new CopyOnWriteArrayList<>();
    private final Coordinate coordinate;

    public Cage(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public void addAnimal(Animals a) {
        animals.add(a);
    }

    ;

    public void addPlant(Plants p) {
        plants.add(p);
    }

    public void removePlant(Plants p) {
        plants.remove(p);
    }

    public void removeAnimal(Animals a) {
        animals.remove(a);
    }

    ;

}
