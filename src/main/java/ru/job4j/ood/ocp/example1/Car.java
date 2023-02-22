package ru.job4j.ood.ocp.example1;

import ru.job4j.ood.ocp.Moveable;

import java.util.ArrayList;

public class Car implements Moveable {
    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void move() {
        ArrayList<Point> waypoints = new ArrayList<>();
        for (Point point : waypoints) {
            System.out.printf("Машина в точке %d %d", point.x, point.y);
        }
    }
}
