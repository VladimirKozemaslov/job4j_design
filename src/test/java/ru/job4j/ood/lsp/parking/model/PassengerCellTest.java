package ru.job4j.ood.lsp.parking.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PassengerCellTest {

    @Test
    public void whenTakeCarToCell() {
        AbstractCar car = new PassengerCar("A111AA777");
        ParkingCell cell = new PassengerCell("1A");

        cell.take(car);
        assertThat(cell).hasFieldOrPropertyWithValue("car", car);
    }

    @Test
    public void whenNotTakeCarToCell() {
        AbstractCar car = new PassengerCar("Б222ББ333");
        ParkingCell cell = new CargoCell("4В");
        boolean rsl = cell.take(car);
        assertThat(rsl).isFalse();
    }

    @Test
    public void whenClearCell() {
        ParkingCell cell = new PassengerCell("1A");
        cell.clear();
        assertThat(cell).hasFieldOrPropertyWithValue("car", null);
    }
}