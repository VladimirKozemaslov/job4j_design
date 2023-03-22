package ru.job4j.ood.isp.example2;

public class Stove implements Appliance {
    @Override
    public void turnOn() {
        System.out.println("Stove is on");
    }

    @Override
    public void turnOff() {
        System.out.println("Stove is off");
    }

    @Override
    public void temperatureUp() {
        System.out.println("Temperature increased");
    }

    @Override
    public void temperatureDown() {
        System.out.println("Temperature decreased");
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void switchRegime() {
        throw new UnsupportedOperationException();
    }
}
