package org.example.revitalizationOfLife;

import lombok.Getter;
import lombok.Setter;
import org.example.Cage;
import org.example.Island;
import org.example.population.Plants;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class GrowService {

    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public GrowService(Island island) {
        this.island = island;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(this::grow, 40, 40, TimeUnit.SECONDS);
    }

    public void grow() {
        Random random = new Random();
        for (Cage cage : island.getCages()) {
            int growAmount = random.nextInt(5) + 3;
            for (int i = 0; i < growAmount; i++) {
                cage.addPlant(new Plants(10.0));
            }
        }
        ;
    }

}
