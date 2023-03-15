package ru.job4j.ood.lsp.parking.model;

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
}
