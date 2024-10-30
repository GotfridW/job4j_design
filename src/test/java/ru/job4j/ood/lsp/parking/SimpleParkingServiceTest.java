package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.model.Vehicle;
import ru.job4j.ood.lsp.parking.service.SimpleParkingService;
import ru.job4j.ood.lsp.parking.store.CarAndTruckParkingLot;
import ru.job4j.ood.lsp.parking.store.ParkingLot;

import static org.assertj.core.api.Assertions.*;

class SimpleParkingServiceTest {

    @Test
    void whenAddNullThenException() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(1, 1);
        SimpleParkingService service = new SimpleParkingService(parkingLot);
        assertThatThrownBy(() -> service.park(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAddCarWithNullPlateThenException() {
        assertThatThrownBy(() -> new Car(null, "Toyota"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAddCarWithNullModelThenException() {
        assertThatThrownBy(() -> new Car("624yra", null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void serviceParkTest() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(1, 0);
        SimpleParkingService service = new SimpleParkingService(parkingLot);
        Vehicle car = new Car("257qwe", "Toyota");
        assertThatNoException().isThrownBy(() -> service.park(car));
    }

    @Test
    void whenNoSpacesThenParkThrowsException() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(0, 0);
        SimpleParkingService service = new SimpleParkingService(parkingLot);
        Vehicle vehicle = new Car("543tgi", "Toyota");
        assertThatThrownBy(() -> service.park(vehicle)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void whenLeave() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 3);
        SimpleParkingService service = new SimpleParkingService(parkingLot);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        service.park(vehicle1);
        assertThatNoException().isThrownBy(() -> service.leave(vehicle1));
    }

    @Test
    void whenLeaveNonExistentVehicleThenException() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 0);
        SimpleParkingService service = new SimpleParkingService(parkingLot);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Car("764hvn", "Honda");
        service.park(vehicle1);
        assertThatThrownBy(() -> service.leave(vehicle2)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void whenGetParkedVehicles() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 3);
        SimpleParkingService service = new SimpleParkingService(parkingLot);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Truck("546gth", "Volvo", 3);
        Vehicle vehicle3 = new Car("764hvn", "Honda");
        service.park(vehicle1);
        service.park(vehicle2);
        service.park(vehicle3);
        String expected = String.join(System.lineSeparator(),
                        "VEHICLES PARKED:",
                        "Vehicle(licencePlate=257qwe, model=Toyota)",
                        "Vehicle(licencePlate=764hvn, model=Honda)",
                        "Vehicle(licencePlate=546gth, model=Volvo)", "");
        assertThat(service.getParkedVehicles()).isEqualTo(expected);
    }
}