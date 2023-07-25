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
        this.licencePlate = licencePlate;
        this.model = model;
    }
}
