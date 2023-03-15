package ru.job4j.ood.lsp.parking.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PassengerCellTest {
    @Disabled
    @Test
    public void whenTakeCarToCell() {
        Car car = new PassengerCar("A111AA777");
        ParkingCell cell = new PassengerCell("1A");

        cell.take(car);
        assertThat(cell).hasFieldOrPropertyWithValue("car", car);
    }

    @Disabled
    @Test
    public void whenNotTakeCarToCell() {
        Car car = new CargoCar("Б222ББ333");
        ParkingCell cell = new PassengerCell("4В");
        boolean rsl = cell.take(car);
        assertThat(rsl).isFalse();
    }

    @Disabled
    @Test
    public void whenClearCell() {
        Car car = new PassengerCar("A111AA777");
        ParkingCell cell = new PassengerCell("1A");
        cell.clear();
        assertThat(cell).hasFieldOrPropertyWithValue("car", null);
    }
}