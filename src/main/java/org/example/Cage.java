package org.example;

import org.example.population.Animals;
import org.example.population.Plants;

import java.util.List;

public class Cage {

    private final List<Animals> animals;
    private Plants plant;

    public Cage(List<Animals> animals, Plants plant) {
        this.animals = animals;
        this.plant = plant;
    }


    public void addAnimal(Animals a){};

    public void removeAnimal(Animals a){};

    public void getAnimals(){};

    public void getPlant(){};

    public void setPlant(Plants p){};

    public void countOf(Class<?> type){};
}
