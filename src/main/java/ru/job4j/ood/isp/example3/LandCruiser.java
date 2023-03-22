package ru.job4j.ood.isp.example3;

public class LandCruiser implements Auto {
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
        System.out.println("4WD mode on");
    }

    @Override
    public void fourWheelDriveOff() {
        System.out.println("4WD mode off");
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
