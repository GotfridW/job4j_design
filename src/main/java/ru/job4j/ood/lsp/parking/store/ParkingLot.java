package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Vehicle;

import java.util.List;

public interface ParkingLot {
    boolean add(Vehicle vehicle);

    boolean delete(Vehicle vehicle);

    List<Vehicle> getAllVehicles();
}
