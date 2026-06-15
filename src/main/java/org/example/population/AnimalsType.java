package org.example.population;

public enum AnimalsType {
    WOLF("Wolf", 2, 30),
    BEAR("Bear", 1, 7),
    RABBIT("Rabbit", 3, 150),
    BOAR("Boar", 1, 50),
    MOUSE("Mouse", 2, 500),
    DUCK("Duck", 3, 500),
    ELK("Elk", 2, 41);

    public final String symbol;
    public final int speed;
    public final int maxPerCell;

    AnimalsType(String symbol, int speed, int maxPerCell) {
        this.symbol = symbol;
        this.speed = speed;
        this.maxPerCell = maxPerCell;
    }
}
