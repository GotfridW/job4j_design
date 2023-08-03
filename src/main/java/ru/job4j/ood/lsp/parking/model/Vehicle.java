package ru.job4j.ood.lsp.parking.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public abstract class Vehicle {
    @EqualsAndHashCode.Include
    private final String licencePlate;
    private final String model;

    public Vehicle(String licencePlate, String model) {
        validate(licencePlate, model);
        this.licencePlate = licencePlate;
        this.model = model;
    }

    private void validate(String licencePlate, String model) {
        if (licencePlate == null) {
            throw new IllegalArgumentException("Invalid input for licence plate");
        }
        if (model == null) {
            throw new IllegalArgumentException("Invalid input for model");
        }
    }

    public abstract int getSize();
}
