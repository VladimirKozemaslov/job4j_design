package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.ParkingCell;

public interface Parking {
    public ParkingCell park(Car car);

    public void unPark(Car car);
}
