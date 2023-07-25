package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Vehicle;

import java.util.List;

public class CarAndTruckParkingLot implements ParkingLot {
    private final int carSpaces;
    private final int truckSpaces;

    public CarAndTruckParkingLot(int carSpaces, int truckSpaces) {
        this.carSpaces = carSpaces;
        this.truckSpaces = truckSpaces;
    }

    public int getCarSpaces() {
        return carSpaces;
    }

    public int getTruckSpaces() {
        return truckSpaces;
    }

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
