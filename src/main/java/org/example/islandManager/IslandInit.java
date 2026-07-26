package org.example.islandManager;


import org.example.Cage;
import org.example.Island;
import org.example.population.IslandObjectFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class IslandInit {

    private final Parameters params;
    private final ExecutorService executor;

    public IslandInit(Parameters parameters, ExecutorService executor) {
        this.params = parameters;
        this.executor = executor;
    }

    public Island init() {
        long start;
        long end;
        Island island = new Island(params.getX(), params.getY());
        start = System.currentTimeMillis();
        if (params.isMultithreaded()) {
            fillMultiThread(island);
        } else {
            fillSingleThread(island);
        }

        end = System.currentTimeMillis();
        System.out.println(start + " " + end);

        return island;
    }

    private void fillMultiThread(Island island) {
        List<Future<?>> futures = new ArrayList<>();
        for (Cage cage : island.getCages()) {
            futures.add(executor.submit(() -> {
                IslandObjectFactory.population(cage);
            }));
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void fillSingleThread(Island island) {
        for (Cage cage : island.getCages()) {
            IslandObjectFactory.population(cage);
        }
    }
}