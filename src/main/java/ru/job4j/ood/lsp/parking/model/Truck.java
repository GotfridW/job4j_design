package ru.job4j.ood.lsp.parking.model;

import lombok.Getter;

@Getter
public class Truck extends Vehicle {
    private final int size;

    public Truck(String licencePlate, String model, int size) {
        super(licencePlate, model);
        if (size < 2) {
            throw new IllegalArgumentException("Truck size must be more than one");
        }
        this.size = size;
    }
}
