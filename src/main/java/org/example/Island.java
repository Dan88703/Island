package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.islandManager.Coordinate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Island {
    private final int size;
    private List<Cage> cages;

    public Island(int size) {
        this.size = size;
        this.cages = new ArrayList<>();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cages.add(new Cage(new Coordinate(i, j)));
    }

    public Cage getCage(Coordinate coordinate) {
        return cages.stream()
                .filter(c -> c.getCoordinate().getX() == coordinate.getX() && c.getCoordinate().getY() == coordinate.getY())
                .findFirst()
                .orElse(null);
    }

    public boolean isAvailable(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }

}
