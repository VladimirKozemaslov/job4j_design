package ru.job4j.ood.lsp.parking.model;

public class CargoCar extends AbstractCar {
    public CargoCar(String regNumber) {
        super(3, regNumber);
    }

    public void setSize(int size) {
        this.size = size;
    }
}
