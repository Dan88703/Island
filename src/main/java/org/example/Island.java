package org.example;

public class Island {

    private final Cage[][] grid = new Cage[5][5];


    public Island() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                grid[i][j] = new Cage(i, j);
    }

    public Cage getCell(int x, int y) {
        return grid[x][y];
    }

    public Cage[][] getGrid() {
        return grid;
    }

    public boolean isAvailable(int x, int y) {
        return x>=0 && y>=0 && x<5 && y<5;
    }

}
