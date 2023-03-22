package ru.job4j.ood.isp.example2;

public class WashingMachine implements Appliance {
    @Override
    public void turnOn() {
        System.out.println("WashingMachine is on");
    }

    @Override
    public void turnOff() {
        System.out.println("WashingMachine is off");
    }

    @Override
    public void temperatureUp() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void temperatureDown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void load() {
        System.out.println("Clothes loaded");
    }

    @Override
    public void switchRegime() {
        System.out.println("Regime switched");
    }
}
