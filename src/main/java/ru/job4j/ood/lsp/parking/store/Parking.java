package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.ParkingCell;
import ru.job4j.ood.ocp.example1.Car;

public interface Parking {
    public ParkingCell park();

    public void unPark(Car car);
}
