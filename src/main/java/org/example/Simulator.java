package org.example;

import org.example.islandManager.IslandInit;
import org.example.population.AllAnimals.Wolf;
import org.example.population.AllAnimals.Rabbit;

import javax.security.auth.login.Configuration;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Simulator {

    private final Island island;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public Simulator(int x, int y) {
        this.island = new Island(x, y);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Write island siza: ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            boolean isInThread = sc.nextBoolean();
            try {
                if ((x >= 5 && x <= 50) && (y >= 5 && y <= 50)) {
                    Simulator simulator = new Simulator(x, y);
                    simulator.init();
                    simulator.run();
                } else {
                    throw new IllegalArgumentException("Invalid island size");
                }
            } catch (Exception e) {
                System.err.println("This island can't exist");
                e.printStackTrace();
            }
        }
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

//        while (true) {
//            for (Cage[] row : island.getGrid()) {
//                for (Cage cage : row) {
//                    new ArrayList<>(cage.getAnimals())
//                            .forEach(animal -> {
//
//                            });
//                }
//            }
//            for (Cage[] row : island.getGrid()) {
//                for (Cage cage : row) {
//                    new ArrayList<>(cage.getAnimals())
//                            .forEach(animal -> {
//                                animal.move(cage, island);
//                            });
//                }
//            }
//            for (Cage[] row : island.getGrid()) {
//                for (Cage cage : row) {
//                    new ArrayList<>(cage.getAnimals())
//                            .forEach(animal -> {
//                                animal.multiply(cage);
//                            });
//                }
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void statistics() {
        int wolf = 0;
        int rabbit = 0;
        int plants = 0;

//        for (Cage[] row : island.getGrid()) {
//            for (Cage cage : row) {
//                wolf += cage.countOf(Wolf.class);
//                rabbit += cage.countOf(Rabbit.class);
//                if (cage.getPlant() != null && cage.getPlant().getAmount() > 0) plants++;
//            }
//        }
        System.out.println("Wolf: " + wolf);
        System.out.println("Rabbit: " + rabbit);
        System.out.println("Plants: " + plants);

    }
}
