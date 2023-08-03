package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarAndTruckParkingLot implements ParkingLot {
    private static final int CAR_SIZE = 1;
    private final List<Vehicle> carList = new ArrayList<>();
    private final List<Vehicle> truckList = new ArrayList<>();
    private int carSpaces;
    private int truckSpaces;

    public CarAndTruckParkingLot(int carSpaces, int truckSpaces) {
        if (carSpaces < 0 || truckSpaces < 0) {
            throw new IllegalArgumentException("Invalid input for size: number must be positive");
        }
        this.carSpaces = carSpaces;
        this.truckSpaces = truckSpaces;

    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean result = false;
        int size = vehicle.getSize();
        boolean isCar = size == CAR_SIZE;
        if (isCar && carSpaces > 0) {
            result = carList.add(vehicle);
            carSpaces--;
        }
        if (!isCar && truckSpaces > 0) {
            result = truckList.add(vehicle);
            truckSpaces--;
        }
        if (!isCar && carSpaces >= size) {
            result = carList.add(vehicle);
            carSpaces -= size;
        }
        return result;
    }

    @Override
    public boolean delete(Vehicle vehicle) {
        boolean result = false;
        int size = vehicle.getSize();
        if (carList.contains(vehicle)) {
            result = carList.remove(vehicle);
            carSpaces += size;
        }
        if (truckList.contains(vehicle)) {
            result = truckList.remove(vehicle);
            truckSpaces++;
        }

        return result;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return Stream.concat(carList.stream(), truckList.stream())
                .collect(Collectors.toList());
    }


}
