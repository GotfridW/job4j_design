package ru.job4j.ood.lsp.parking.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.model.Vehicle;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarAndTruckParkingLotTest {

    @Test
    void whenNegativeParkingLotSizeThenException() {
        assertThatThrownBy(() -> new CarAndTruckParkingLot(-1, -2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAddVehicles() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 3);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Truck("546gth", "Volvo", 3);
        Vehicle vehicle3 = new Car("764hvn", "Honda");
        assertThat(parkingLot.add(vehicle1)).isTrue();
        assertThat(parkingLot.add(vehicle2)).isTrue();
        assertThat(parkingLot.add(vehicle3)).isTrue();
    }

    @Test
    void whenNoCarSpacesThenAddCarisFalse() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(0, 3);
        Vehicle car = new Car("824gyr", "Toyota");
        assertThat(parkingLot.add(car)).isFalse();
    }

    @Test
    void whenNotEnoughTruckSpacesButEnoughCarSpacesThenAddVehicleIsTrue() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(4, 2);
        Vehicle vehicle = new Truck("6347tby", "Volvo", 3);
        assertThat(parkingLot.add(vehicle)).isTrue();
    }

    @Test
    void whenNoSpacesThenAddVehicleIsFalse() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(0, 0);
        Vehicle vehicle = new Truck("5436het", "MAN", 3);
        assertThat(parkingLot.add(vehicle)).isFalse();
    }

    @Test
    void whenGetAllVehicles() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 3);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Truck("546gth", "Volvo", 3);
        Vehicle vehicle3 = new Car("764hvn", "Honda");
        parkingLot.add(vehicle1);
        parkingLot.add(vehicle2);
        parkingLot.add(vehicle3);
        assertThat(parkingLot.getAllVehicles()).hasSize(3)
                .contains(vehicle1, vehicle2, vehicle3);
    }

    @Test
    void whenDeleteVehicle() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 3);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Truck("546gth", "Volvo", 3);
        Vehicle vehicle3 = new Car("764hvn", "Honda");
        parkingLot.add(vehicle1);
        parkingLot.add(vehicle2);
        parkingLot.add(vehicle3);
        assertThat(parkingLot.delete(vehicle2)).isTrue();
        List<Vehicle> expected = List.of(vehicle1, vehicle3);
        assertThat(parkingLot.getAllVehicles()).isEqualTo(expected);
    }

    @Test
    void whenDeleteNonExistentVehicleThenFalse() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 0);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Car("764hvn", "Honda");
        parkingLot.add(vehicle1);
        assertThat(parkingLot.delete(vehicle2)).isFalse();
    }

}