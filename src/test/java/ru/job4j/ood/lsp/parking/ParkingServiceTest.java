package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.model.Vehicle;
import ru.job4j.ood.lsp.parking.store.CarAndTruckParkingLot;
import ru.job4j.ood.lsp.parking.store.ParkingLot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class ParkingServiceTest {
    
    @Test
    void serviceParkTest() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(1, 0);
        ParkingService service = new ParkingService(parkingLot);
        Vehicle car = new Car("257qwe", "Toyota");
        assertThatThrownBy(() -> service.park(car)).doesNotThrowAnyException();
    }

    @Test
    void whenNoSpacesThenParkThrowsException() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(0, 0);
        ParkingService service = new ParkingService(parkingLot);
        Vehicle vehicle = new Car("543tgi", "Toyota");
        assertThatThrownBy(() -> service.park(vehicle)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void whenLeave() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 3);
        ParkingService service = new ParkingService(parkingLot);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        service.park(vehicle1);
        assertThatThrownBy(() -> service.leave(vehicle1)).doesNotThrowAnyException();
    }

    @Test
    void whenLeaveNonExistentVehicleThenException() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 0);
        ParkingService service = new ParkingService(parkingLot);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Car("764hvn", "Honda");
        service.park(vehicle1);
        assertThatThrownBy(() -> service.leave(vehicle2)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void whenGetParkedVehicles() {
        ParkingLot parkingLot = new CarAndTruckParkingLot(2, 3);
        ParkingService service = new ParkingService(parkingLot);
        Vehicle vehicle1 = new Car("257qwe", "Toyota");
        Vehicle vehicle2 = new Truck("546gth", "Volvo", 3);
        Vehicle vehicle3 = new Car("764hvn", "Honda");
        service.park(vehicle1);
        service.park(vehicle2);
        service.park(vehicle3);
        String expected = """
                VEHICLES PARKED:
                Vehicle{licencePlate='257qwe', name='Toyota'}
                Vehicle{licencePlate='764hvn', name='Honda'}
                Vehicle{licencePlate='546gth', name='Volvo'}
                """;
        assertThat(service.getParkedVehicles()).isEqualTo(expected);
    }
}