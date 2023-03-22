package ru.job4j.ood.isp.example3;

public class Bentley implements Auto {
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
        System.out.println("Lifted up");
    }

    @Override
    public void lowerDown() {
        System.out.println("Lowed down");
    }
}
