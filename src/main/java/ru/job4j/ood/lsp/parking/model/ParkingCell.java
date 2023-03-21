package ru.job4j.ood.lsp.parking.model;

public interface ParkingCell {
    boolean take(AbstractCar car);

    void clear();
}
