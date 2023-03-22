package ru.job4j.ood.isp.example2;

public class Dishwasher implements Appliance {
    @Override
    public void turnOn() {
        System.out.println("Dishwasher is on");
    }

    @Override
    public void turnOff() {
        System.out.println("Dishwasher is off");
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
        System.out.println("Dishes loaded");
    }

    @Override
    public void switchRegime() {
        System.out.println("Regime switched");
    }
}
