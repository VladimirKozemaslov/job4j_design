package ru.job4j.ood.lsp.parking.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AutoParkingTest {
    @Test
    public void whenSuccessfullyParkPassengerCar() {
        Parking parking = new AutoParking(10, 5);
        AbstractCar car = new PassengerCar("Б222ББ333");
        List<AbstractCell> parkingCells = parking.park(car);
        AbstractCell testCell = new PassengerCell("A1");
        testCell.take(car);
        assertThat(parkingCells.size()).isEqualTo(1);
        assertThat(parkingCells.get(0)).isEqualTo(testCell);
    }

    @Test
    public void whenNotSuccessfullyParkPassengerCar() {
        Parking parking = new AutoParking(0, 5);
        AbstractCar car = new PassengerCar("Б222ББ333");
        List<AbstractCell> parkingCells = parking.park(car);
        assertThat(parkingCells.size()).isEqualTo(0);
    }

    @Test
    public void whenSuccessfullyParkCargoCarOnCargoCell() {
        Parking parking = new AutoParking(5, 5);
        AbstractCar car = new CargoCar("Б222ББ333");
        List<AbstractCell> parkingCells = parking.park(car);
        ParkingCell testCell = new CargoCell("B1");
        testCell.take(car);
        assertThat(parkingCells.size()).isEqualTo(1);
        assertThat(parkingCells.get(0)).isEqualTo(testCell);
    }

    @Test
    public void whenSuccessfullyParkCargoCarOnPassengerCells() {
        Parking parking = new AutoParking(5, 5);
        AbstractCar cargoCar1 = new CargoCar("A123AA333");
        AbstractCar cargoCar2 = new CargoCar("A124AA333");
        AbstractCar cargoCar3 = new CargoCar("A125AA333");
        AbstractCar cargoCar4 = new CargoCar("A126AA333");
        AbstractCar cargoCar5 = new CargoCar("A127AA333");
        parking.park(cargoCar1);
        parking.park(cargoCar2);
        parking.park(cargoCar3);
        parking.park(cargoCar4);
        parking.park(cargoCar5);

        AbstractCar cargoCar6 = new CargoCar("Б222ББ333");
        List<AbstractCell> parkingCells = parking.park(cargoCar6);
        ParkingCell testCell1 = new PassengerCell("A1");
        ParkingCell testCell2 = new PassengerCell("A2");
        ParkingCell testCell3 = new PassengerCell("A3");
        testCell1.take(cargoCar6);
        testCell2.take(cargoCar6);
        testCell3.take(cargoCar6);
        assertThat(parkingCells.size()).isEqualTo(3);
        assertThat(parkingCells.get(0)).isEqualTo(testCell1);
        assertThat(parkingCells.get(1)).isEqualTo(testCell2);
        assertThat(parkingCells.get(2)).isEqualTo(testCell3);
    }

    @Test
    public void whenNotSuccessfullyParkCargoCar() {
        Parking parking = new AutoParking(5, 5);
        AbstractCar passengerCar1 = new PassengerCar("Б222ББ333");
        AbstractCar passengerCar2 = new PassengerCar("Б223ББ333");
        AbstractCar passengerCar3 = new PassengerCar("Б224ББ333");
        AbstractCar cargoCar1 = new CargoCar("A123AA333");
        AbstractCar cargoCar2 = new CargoCar("A124AA333");
        AbstractCar cargoCar3 = new CargoCar("A125AA333");
        AbstractCar cargoCar4 = new CargoCar("A126AA333");
        AbstractCar cargoCar5 = new CargoCar("A127AA333");
        parking.park(cargoCar1);
        parking.park(cargoCar2);
        parking.park(cargoCar3);
        parking.park(cargoCar4);
        parking.park(cargoCar5);
        parking.park(passengerCar1);
        parking.park(passengerCar2);
        parking.park(passengerCar3);

        AbstractCar cargoCar6 = new CargoCar("A128AA333");
        List<AbstractCell> parkingCell = parking.park(cargoCar6);
        assertThat(parkingCell.size()).isEqualTo(0);
    }

    @Test
    public void whenSuccessfullyUnParkPassengerCar() {
        Parking parking = new AutoParking(5, 5);
        AbstractCar passengerCar = new PassengerCar("Б222ББ333");
        List<AbstractCell> parkingCells = parking.park(passengerCar);
        List<AbstractCell> testCells = parking.unPark(passengerCar);
        assertThat(parkingCells.size()).isEqualTo(testCells.size());
        assertThat(parkingCells.get(0)).isEqualTo(testCells.get(0));
    }
}