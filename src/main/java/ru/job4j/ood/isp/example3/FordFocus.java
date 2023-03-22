package ru.job4j.ood.isp.example3;

public class FordFocus implements Auto {
    @Override
    public void gas() {
        System.out.println("Accelerated");
    }

    @Override
    public void brake() {
        System.out.println("Slowed");
    }

    @Override
    public void fourWheelDriveOn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fourWheelDriveOff() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void liftUp() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void lowerDown() {
        throw new UnsupportedOperationException();
    }
}
