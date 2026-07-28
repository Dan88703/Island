package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.islandManager.Coordinate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Island {
    private int sizeX;
    private int sizeY;
    private List<Cage> cages;

    public Island(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cages = new ArrayList<>();
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++)
                cages.add(new Cage(new Coordinate(i, j)));
    }

    public Cage getCage(Coordinate coordinate) {
        return cages.stream()
                .filter(c -> c.getCoordinate().getX() == coordinate.getX() && c.getCoordinate().getY() == coordinate.getY())
                .findFirst()
                .orElse(null);
    }

    public boolean isAvailable(int x, int y) {
        return x >= 0 && y >= 0 && x < sizeX && y < sizeY;
    }

}
