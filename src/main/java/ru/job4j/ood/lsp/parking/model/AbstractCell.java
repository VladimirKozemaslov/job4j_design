package ru.job4j.ood.lsp.parking.model;

public abstract class AbstractCell implements ParkingCell {
    protected int size;
    protected Car car;
    protected String id;

    public AbstractCell(int size, String id) {
        this.size = size;
        this.id = id;
    }

    @Override
    public boolean take(Car car) {
        boolean rsl = false;
        return rsl;
    }

    @Override
    public void clear() {
        car = null;
    }
}
