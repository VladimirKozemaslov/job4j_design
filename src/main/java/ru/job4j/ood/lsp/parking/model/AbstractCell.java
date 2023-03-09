package ru.job4j.ood.lsp.parking.model;

public class AbstractCell implements ParkingCell {
    protected int size;
    protected Car car;
    protected String id;

    public AbstractCell(int size) {
        this.size = size;
    }

    @Override
    public void take(Car car) {
        this.car = car;
    }

    @Override
    public void clear() {
        car = null;
    }
}
