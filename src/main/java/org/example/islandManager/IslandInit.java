package org.example.islandManager;


import org.example.AnimalFactory;
import org.example.Cage;
import org.example.Island;
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
        List<Future<?>> futures = new ArrayList<>();
        for (Cage cage : island.getCages()) {
            futures.add(executor.submit(() -> {
                Random rnd = new Random();
                int wolfCount = rnd.nextInt(15) + 1;
                int rabbitCount = rnd.nextInt(15) + 1;
                int plantCount = rnd.nextInt(15) + 1;

                for (int i = 0; i < wolfCount; i++)
                    cage.addAnimal(AnimalFactory.createAnimal("WOLF"));
                for (int i = 0; i < rabbitCount; i++)
                    cage.addAnimal(AnimalFactory.createAnimal("RABBIT"));
                for (int i = 0; i < plantCount; i++)
                    cage.addPlant(new Plants(10.0));
            }));
        }
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}