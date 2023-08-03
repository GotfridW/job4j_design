package ru.job4j.ood.lsp.parking.model;

public class Car extends Vehicle {
    private static final int SIZE = 1;

    public Car(String licencePlate, String model) {
        super(licencePlate, model);
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}
