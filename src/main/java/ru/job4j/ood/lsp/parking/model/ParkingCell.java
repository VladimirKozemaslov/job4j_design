package ru.job4j.ood.lsp.parking.model;

public interface ParkingCell {
    public boolean take(Car car);

    public void clear();
}
