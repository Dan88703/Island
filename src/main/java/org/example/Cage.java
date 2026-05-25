package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.population.Animals;
import org.example.population.Plants;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Getter
@Setter

public class Cage {

    private final List<Animals> animals = new CopyOnWriteArrayList<>();
    private Plants plant;
    private final int x;
    private final int y;
    public Cage(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public void addAnimal(Animals a){
        animals.add(a);
    };

    public void removeAnimal(Animals a){
        animals.remove(a);
    };



    public long countOf(Class<?> type){
        return animals.stream().filter(a -> a.getClass() == type).count();
    };
}
