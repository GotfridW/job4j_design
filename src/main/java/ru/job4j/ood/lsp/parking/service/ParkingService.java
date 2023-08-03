package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Vehicle;

public interface ParkingService {
    void park(Vehicle vehicle);
    void leave(Vehicle vehicle);
    String getParkedVehicles();
}
