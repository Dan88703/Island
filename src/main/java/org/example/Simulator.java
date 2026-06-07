package org.example;

import org.example.islandManager.IslandInit;
import org.example.population.AllAnimals.Wolf;
import org.example.population.AllAnimals.Rabbit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Simulator {

    private final Island island;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public Simulator(int islandSize) {
        this.island = new Island(islandSize);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Write island siza: ");
        int islandSize = sc.nextInt();
        Simulator simulator = new Simulator(islandSize);
        simulator.init();
        simulator.run();
    }

    public void init() {
        IslandInit islandInit = new IslandInit(island, executor);
        islandInit.init();
    }

    public void run() {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            statistics();
        }, 0, 15, TimeUnit.SECONDS);

        while (true) {
            for (Cage[] row : island.getGrid()) {
                for (Cage cage : row) {
                    new ArrayList<>(cage.getAnimals())
                            .forEach(animal -> {

                            });
                }
            }
            for (Cage[] row : island.getGrid()) {
                for (Cage cage : row) {
                    new ArrayList<>(cage.getAnimals())
                            .forEach(animal -> {
                                animal.move(cage, island);
                            });
                }
            }
            for (Cage[] row : island.getGrid()) {
                for (Cage cage : row) {
                    new ArrayList<>(cage.getAnimals())
                            .forEach(animal -> {
                                animal.multiply(cage);
                            });
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void statistics() {
        int wolf = 0;
        int rabbit = 0;
        int plants = 0;

        for (Cage[] row : island.getGrid()) {
            for (Cage cage : row) {
                wolf += cage.countOf(Wolf.class);
                rabbit += cage.countOf(Rabbit.class);
                if (cage.getPlant() != null && cage.getPlant().getAmount() > 0) plants++;
            }
        }
        System.out.println("Wolf: " + wolf);
        System.out.println("Rabbit: " + rabbit);
        System.out.println("Plants: " + plants);

    }
}
