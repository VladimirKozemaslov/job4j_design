package ru.job4j.ood.isp.example1;

public class Seal implements Animal {
    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void walk() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void swim() {
        System.out.println("Seal is swimming.");
    }
}
