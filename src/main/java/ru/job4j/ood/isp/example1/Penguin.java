package ru.job4j.ood.isp.example1;

public class Penguin implements Animal {
    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void walk() {
        System.out.println("Penguin is walking.");
    }

    @Override
    public void swim() {
        System.out.println("Penguin is swimming.");
    }
}
