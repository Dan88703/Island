package org.example;

import org.example.population.Animals;
import org.example.population.Plants;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cage {

    private final List<Animals> animals = new CopyOnWriteArrayList<>();
    private Plants plant;
    private final int x;
    private final int y;
    public Cage(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void addAnimal(Animals a){
        animals.add(a);
    };

    public void removeAnimal(Animals a){
        animals.remove(a);
    };

    public List<Animals> getAnimals(){
        return animals;
    };

    public Plants getPlant(){
        return plant;
    };

    public void setPlant(Plants p){
        this.plant = p;
    };

    public long countOf(Class<?> type){
        return animals.stream().filter(a -> a.getClass() == type).count();
    };
}
