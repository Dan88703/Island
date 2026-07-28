package org.example;

import org.example.islandManager.IslandInit;
import org.example.population.AnimalsType;
import org.example.revitalizationOfLife.EatService;
import org.example.revitalizationOfLife.GrowService;
import org.example.revitalizationOfLife.MoveService;
import org.example.islandManager.Parameters;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Simulator {
    private final Parameters params;
    private final Island island;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    private EatService eat;
    private MoveService move;
    private GrowService grow;

    public Simulator(Parameters params) {
        this.params = params;
        this.island = new IslandInit(params,executor).init();
    }

    public static void main(String[] args) throws InterruptedException {
        Parameters parameters = initParams();
        if ((parameters.getX() >= 5 && parameters.getX() <= 50) && (parameters.getY() >= 5 && parameters.getY() <= 50)) {
            Simulator simulator = new Simulator(parameters);
            if (parameters.isMultithreaded()) {
                simulator.runMultiThread();
            } else {
                simulator.runSingleThread();
            }
        } else {
            throw new IllegalArgumentException("Invalid island size");
        }
    }

    public static Parameters initParams() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Write island width: ");
        int x = sc.nextInt();
        System.out.print("Write island height: ");
        int y = sc.nextInt();
        System.out.print("What kind of way do you want to do? : 1 Thread or Many Threads");
        String isInThread = sc.next();
        System.out.println("Do you want to track animals?");
        System.out.println("Y/n");
        String isTruck = sc.next();
        boolean isMultiThreaded = isInThread.equals("many");
        boolean isLogging = !isTruck.equals("n");
        return new Parameters(isMultiThreaded, x, y, isLogging);
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




    private void initServices() {
        EvanteLogger logger = new EvanteLogger(params.isLoggingEnabled());
        eat = new EatService(island);
        move = new MoveService(island, logger);
        grow = new GrowService(island);
        grow.start();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::statistics, 0, 40, TimeUnit.SECONDS);
    }

    public void runMultiThread() throws InterruptedException {
        initServices();
        while (true) {
            List<Future<?>> futures = new ArrayList<>();
            futures.add(executor.submit(eat::process));
            futures.add(executor.submit(move::process));
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
        initServices();
        while (true) {
            eat.process();
            move.process();
            Thread.sleep(2000);
        }
    }


}
