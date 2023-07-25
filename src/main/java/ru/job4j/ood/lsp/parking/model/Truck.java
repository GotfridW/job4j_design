package ru.job4j.ood.lsp.parking.model;

public class Truck extends Vehicle {
    private final int size;

    public Truck(String licencePlate, String model, int size) {
        super(licencePlate, model);
        this.size = size;
    }
}
