package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Vehicle;

import java.util.List;

public class CarAndTruckParkingLot implements ParkingLot {

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean delete(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return null;
    }
}
