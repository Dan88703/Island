package org.example;

import org.example.islandManager.IslandInit;
import org.example.population.AllAnimals.*;
import org.example.revitalizationOfLife.EatService;
import org.example.revitalizationOfLife.GrowService;
import org.example.revitalizationOfLife.MoveService;


import java.util.ArrayList;
import java.util.List;
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
            System.out.print("Write island width: ");
            int x = sc.nextInt();
            System.out.print("Write island height: ");
            int y = sc.nextInt();
            System.out.print("What kind of way do you want to do? : 1 Thread or Many Threads");
            String isInThread = sc.next();
            try {
                if ((x >= 5 && x <= 50) && (y >= 5 && y <= 50)) {
                    Simulator simulator = new Simulator(x, y);
                    simulator.init();
                    if (isInThread.equals("many")) {
                        simulator.runMultiThread();
                    } else {
                        simulator.runSingleThread();
                    }
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


    }

    public void statistics() {
        System.out.println("=== Statistic ===");
        for (AnimalsType animalsType : AnimalsType.values()) {
           long count = island.getCages().stream()
                   .flatMap(cage -> cage.getAnimals().stream()
                           .filter(animals -> animals.getAnimalsType() == animalsType))
                   .count();
           System.out.println("Animal:" + animalsType.symbol + " " + count);

        }
        long plants = island.getCages().stream()
                .mapToLong(cage -> cage.getPlants().size())
                .sum();

        System.out.println("Plants:" + plants);

    }

    public void runMultiThread() throws InterruptedException {
        EatService eat = new EatService(island);
        MoveService move = new MoveService(island);
        GrowService grow = new GrowService(island);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            statistics();
        }, 0, 15, TimeUnit.SECONDS);

        grow.start();

        while (true) {
            List<Future<?>> futures = new ArrayList<>();
            futures.add(executor.submit(eat::process));
            futures.add(executor.submit(move::procces));

            for (Future<?> f : futures) {
                try {
                    f.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(3000);
        }
    }

    public void runSingleThread() throws InterruptedException {
        EatService eat = new EatService(island);
        MoveService move = new MoveService(island);
        GrowService grow = new GrowService(island);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            statistics();
        }, 0, 15, TimeUnit.SECONDS);

        grow.start();

        while (true) {
            eat.process();
            move.procces();
            Thread.sleep(2000);
        }

    }

}
