package ru.job4j.ood.lsp.parking.model;

public class AbstractCar implements Car {
    protected int size;
    protected String regNumber;

    public AbstractCar(int size, String regNumber) {
        this.size = size;
        this.regNumber = regNumber;
    }
}
