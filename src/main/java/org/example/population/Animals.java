package org.example.population;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class Animals {
    protected final String id = UUID.randomUUID().toString();
    protected boolean alive = true;
    protected AnimalsType animalsType;

    public Animals(AnimalsType animalsType) {
        this.animalsType = animalsType;
    }

}
