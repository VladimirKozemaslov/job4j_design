package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.AbstractCar;
import ru.job4j.ood.lsp.parking.model.AbstractCell;

import java.util.List;

public interface Parking {
    List<AbstractCell> park(AbstractCar car);

    List<AbstractCell> unPark(AbstractCar car);
}
