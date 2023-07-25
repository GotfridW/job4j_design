package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.model.Vehicle;
import ru.job4j.ood.lsp.parking.store.ParkingLot;

public class ParkingService {
    private final ParkingLot parkingLot;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void park(Vehicle vehicle) {
    }

    public void leave(Vehicle vehicle) {
    }

    public String getParkedVehicles() {
        return null;
    }
}
