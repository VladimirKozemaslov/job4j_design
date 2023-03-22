package ru.job4j.ood.isp.example1;

public class Bear implements Animal {
    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void walk() {
        System.out.println("Bear is walking.");
    }

    @Override
    public void swim() {
        System.out.println("Bear is swimming.");
    }
}
