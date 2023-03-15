package ru.job4j.ood.lsp.parking.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CargoCellTest {
    @Disabled
    @Test
    public void whenTakeCarToCell() {
        Car car = new CargoCar("Б222ББ333");
        ParkingCell cell = new CargoCell("5A");
        boolean rsl = cell.take(car);
        assertThat(rsl).isTrue();
    }

    @Disabled
    @Test
    public void whenNotTakeCarToCell() {
        Car car = new CargoCar("Б222ББ333");
        Car car2 = new CargoCar("Б333ББ444");
        ParkingCell cell = new CargoCell("4В");
        cell.take(car);
        boolean rsl = cell.take(car2);
        assertThat(rsl).isFalse();
    }

    @Disabled
    @Test
    public void whenClearCell() {
        Car car = new CargoCar("A111AA777");
        ParkingCell cell = new CargoCell("5A");
        cell.take(car);
        cell.clear();
        assertThat(cell).hasFieldOrPropertyWithValue("car", null);
    }
}