package org.example.revitalizationOfLife;

import org.example.Cage;
import org.example.Island;
import org.example.islandManager.Coordinate;
import org.example.population.Animals;

import java.util.ArrayList;
import java.util.Random;

public class MoveService {
    private final Island island;

    public MoveService(Island island) {
        this.island = island;
    }

    public void procces() {
        for (Cage cage : island.getCages()) {
            new ArrayList<>(cage.getAnimals())
                    .forEach(animal -> move(animal, cage));
        }
    }

    private void move(Animals animal, Cage cage) {
        int x = cage.getCoordinate().getX();
        int y = cage.getCoordinate().getY();

        int direction = new Random().nextInt(4);

        for (int i = 0; i < animal.getAnimalsType().speed; i++) {
            int newX = x;
            int newY = y;

            if (direction == 0) newY--;      // вверх
            else if (direction == 1) newY++; // вниз
            else if (direction == 2) newX--; // влево
            else newX++;                     // вправо

            if (island.isAvailable(newX, newY)) {
                x = newX;
                y = newY;
            }
        }

        Cage newCage = island.getCage(new Coordinate(x, y));
        if (newCage != cage) {
            cage.removeAnimal(animal);
            newCage.addAnimal(animal);
        }
    }
}
