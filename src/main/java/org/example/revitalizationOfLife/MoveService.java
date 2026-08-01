package org.example.revitalizationOfLife;

import org.example.Cage;
import org.example.EvanteLogger;
import org.example.Island;
import org.example.islandManager.Coordinate;
import org.example.population.Animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoveService {
    private final Island island;
    private final EvanteLogger track;


    public MoveService(Island island, EvanteLogger track) {
        this.island = island;
        this.track = track;
    }

    private List<Direction> getAvailableDirections(int x, int y) {
        List<Direction> directions = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (island.isAvailable(x + direction.dx, y + direction.dy)) {
                directions.add(direction);
            }
        }
        return directions;
    }

    public void process() {
        for (Cage cage : island.getCages()) {
            new ArrayList<>(cage.getAnimals())
                    .forEach(animal -> move(animal, cage));
        }
    }

    private void move(Animals animal, Cage cage) {
        int curentX = cage.getCoordinate().getX();
        int curentY = cage.getCoordinate().getY();
        List<Direction> currentDirections = getAvailableDirections(curentX, curentY);
        Direction direction = currentDirections.get(new Random().nextInt(currentDirections.size()));
        for (int i = 0; i < animal.getAnimalsType().speed; i++) {
            int newX = curentX + direction.dx;
            int newY = curentY + direction.dy;
            if (island.isAvailable(newX, newY)) {
                curentX = newX;
                curentY = newY;
            }
        }
        Cage newCage = island.getCage(new Coordinate(curentX, curentY));
        if (newCage != cage) {
            track.log(animal.getAnimalsType().symbol + " moved from"
                    + cage.getCoordinate().getX() + "," + cage.getCoordinate().getY()
                    + " to " + newCage.getCoordinate().getX() + "," + newCage.getCoordinate().getY());
            cage.removeAnimal(animal);
            newCage.addAnimal(animal);
        }
    }

}
