package org.example.islandManager;


import org.example.AnimalFactory;
import org.example.Cage;
import org.example.Island;
import org.example.population.AnimalsType;
import org.example.population.Plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class IslandInit {
    private final Island island;
    private final ExecutorService executor;

    public IslandInit(Island island, ExecutorService executor) {
        this.island = island;
        this.executor = executor;
    }

    public void init() {
        long start = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<>();
        for (Cage cage : island.getCages()) {
            futures.add(executor.submit(() -> {
                Random rnd = new Random();

                for (AnimalsType type : AnimalsType.values()) {
                    int count = rnd.nextInt(15) + 1;
                    for (int i = 0; i < count; i++) {
                        cage.addAnimal(AnimalFactory.createAnimal(type));
                    }
                }
                int plantCount = rnd.nextInt(15) + 1;
                for (int i = 0; i < plantCount; i++) {
                    cage.addPlant(new Plants(10.0));
                }
            }));
        }

        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(start + " " + end);
    }
}