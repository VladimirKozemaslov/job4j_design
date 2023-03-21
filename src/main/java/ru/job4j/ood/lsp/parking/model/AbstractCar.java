package ru.job4j.ood.lsp.parking.model;

import java.util.Objects;

public abstract class AbstractCar implements Car {
    protected int size;
    protected String regNumber;

    public AbstractCar(int size, String regNumber) {
        this.size = size;
        this.regNumber = regNumber;
    }

    public int getSize() {
        return size;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCar that = (AbstractCar) o;
        return size == that.size && Objects.equals(regNumber, that.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, regNumber);
    }
}
