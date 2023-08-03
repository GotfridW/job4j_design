package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Vehicle;
import ru.job4j.ood.lsp.parking.store.ParkingLot;

public class SimpleParkingService implements ParkingService {
    private final ParkingLot parkingLot;

    public SimpleParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Input was null");
        }
        if (!parkingLot.add(vehicle)) {
            throw new IllegalStateException("No space available");
        }
    }

    @Override
    public void leave(Vehicle vehicle) {
        if (!parkingLot.delete(vehicle)) {
            throw new IllegalStateException("Vehicle not found");
        }
    }

    @Override
    public String getParkedVehicles() {
        String ln = System.lineSeparator();
        StringBuilder builder = new StringBuilder("VEHICLES PARKED:" + ln);
        parkingLot.getAllVehicles().forEach(
                vehicle -> builder.append(vehicle).append(ln));
        return builder.toString();
    }
}
