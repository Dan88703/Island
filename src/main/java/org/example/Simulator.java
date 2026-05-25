package org.example;

import org.example.population.Plants;
import org.example.population.AllAnimals.Wolf;
import org.example.population.AllAnimals.Rabbit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Simulator {
    private final Island island = new Island();
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.init();
        simulator.run();
    }

    public void init() {
        List<Future<?>> futures = new ArrayList<>();
        for (Cage[] row : island.getGrid()) {
            for (Cage cage : row) {
                futures.add(executor.submit(() -> {
                    Random rnd = new Random();
                    int wolf = rnd.nextInt(15) + 1;
                    int rabbit = rnd.nextInt(15) + 1;
                    int plants = rnd.nextInt(15) + 1;
                    for (int i = 0; i < wolf; i++) {
                        cage.addAnimal(AnimalFactory.createAnimal("WOLF"));
                    }
                    for (int i = 0; i < rabbit; i++) {
                        cage.addAnimal(AnimalFactory.createAnimal("RABBIT"));
                    }
                    for (int i = 0; i < plants; i++) {
                        cage.setPlant(new Plants(10.0));
                    }

                }));
            }
        }
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
                                animal.eat(cage);
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
            for(Cage[] row : island.getGrid()) {
                for (Cage cage : row) {
                    new ArrayList<>(cage.getAnimals())
                            .forEach(animal -> {
                                animal.multiply(cage);
                            });
                }
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
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
