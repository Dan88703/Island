package org.example.population;

public abstract class Animals implements LifeCycle{

    protected String symbol;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected int maxHunger;
    protected double satiety;
    protected boolean alive = true;

    public Animals(String symbol, int maxPerCell, int speed, double satiety, double foodNeeded, int maxHunger) {
        this.symbol = symbol;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.satiety = satiety;
        this.foodNeeded = foodNeeded;
        this.maxHunger = maxHunger;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public void setMaxPerCell(int maxPerCell) {
        this.maxPerCell = maxPerCell;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(double foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
