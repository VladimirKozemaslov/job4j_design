package ru.job4j.ood.lsp.parking.model;

import java.util.Objects;

public abstract class AbstractCell implements ParkingCell {
    protected int size;
    protected Car car;
    protected String id;

    public AbstractCell(int size, String id) {
        this.size = size;
        this.id = id;
    }

    @Override
    public boolean take(AbstractCar car) {
        boolean rsl = false;
        if (this.car == null && this.size <= car.getSize()) {
            this.car = car;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void clear() {
        car = null;
    }

    public boolean isEmpty() {
        return car == null;
    }

    public int getSize() {
        return size;
    }

    public Car getCar() {
        return car;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCell cell = (AbstractCell) o;
        return size == cell.size && Objects.equals(car, cell.car) && Objects.equals(id, cell.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, car, id);
    }
}
