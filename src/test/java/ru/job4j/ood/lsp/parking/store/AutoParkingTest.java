package ru.job4j.ood.lsp.parking.store;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.*;

import static org.assertj.core.api.Assertions.*;

public class AutoParkingTest {
    @Disabled
    @Test
    public void whenSuccessfullyParkPassengerCar() {
        Parking parking = new AutoParking(10, 5);
        Car car = new PassengerCar("Б222ББ333");
        ParkingCell parkingCell = parking.park(car);
        ParkingCell testCell = new PassengerCell("A1");
        testCell.take(car);
        assertThat(parkingCell).isEqualTo(testCell);
    }

    @Disabled
    @Test
    public void whenNotSuccessfullyParkPassengerCar() {
        Parking parking = new AutoParking(10, 5);
        Car car = new PassengerCar("Б222ББ333");
        ParkingCell parkingCell = parking.park(car);
        assertThat(parkingCell).isNull();
    }

    @Disabled
    @Test
    public void whenSuccessfullyParkCargoCarOnCargoCell() {
        Parking parking = new AutoParking(5, 5);
        Car car = new CargoCar("Б222ББ333");
        ParkingCell parkingCell = parking.park(car);
        ParkingCell testCell = new CargoCell("B1");
        testCell.take(car);
        assertThat(parkingCell).isEqualTo(testCell);
    }

    @Disabled
    @Test
    public void whenSuccessfullyParkCargoCarOnPassengerCells() {
        Parking parking = new AutoParking(5, 5);
        Car cargoCar1 = new CargoCar("A123AA333");
        Car cargoCar2 = new CargoCar("A124AA333");
        Car cargoCar3 = new CargoCar("A125AA333");
        Car cargoCar4 = new CargoCar("A126AA333");
        Car cargoCar5 = new CargoCar("A127AA333");
        parking.park(cargoCar1);
        parking.park(cargoCar2);
        parking.park(cargoCar3);
        parking.park(cargoCar4);
        parking.park(cargoCar5);

        Car cargoCar6 = new CargoCar("Б222ББ333");
        ParkingCell parkingCell = parking.park(cargoCar6);
        ParkingCell testCell = new CargoCell("A1,A2,A3");
        testCell.take(cargoCar6);
        assertThat(parkingCell).isEqualTo(testCell);
    }

    @Disabled
    @Test
    public void whenNotSuccessfullyParkCargoCar() {
        Parking parking = new AutoParking(5, 5);
        Car passengerCar1 = new PassengerCar("Б222ББ333");
        Car passengerCar2 = new PassengerCar("Б223ББ333");
        Car passengerCar3 = new PassengerCar("Б224ББ333");
        Car cargoCar1 = new CargoCar("A123AA333");
        Car cargoCar2 = new CargoCar("A124AA333");
        Car cargoCar3 = new CargoCar("A125AA333");
        Car cargoCar4 = new CargoCar("A126AA333");
        Car cargoCar5 = new CargoCar("A127AA333");
        parking.park(cargoCar1);
        parking.park(cargoCar2);
        parking.park(cargoCar3);
        parking.park(cargoCar4);
        parking.park(cargoCar5);
        parking.park(passengerCar1);
        parking.park(passengerCar2);
        parking.park(passengerCar3);

        Car cargoCar6 = new CargoCar("A128AA333");
        ParkingCell parkingCell = parking.park(cargoCar6);
        assertThat(parkingCell).isNull();
    }

    @Disabled
    @Test
    public void whenSuccessfullyUnParkPassengerCar() {
        Parking parking = new AutoParking(5, 5);
        Car passengerCar = new PassengerCar("Б222ББ333");
        ParkingCell cell = parking.park(passengerCar);
        parking.unPark(passengerCar);
        assertThat(cell).hasFieldOrPropertyWithValue("car", null);
    }
}