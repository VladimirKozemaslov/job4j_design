package ru.job4j.ood.ocp.example3;


import java.util.ArrayList;

public class Car {

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void move(ArrayList<Point> waypoints) {
        for (Point point : waypoints) {
            System.out.printf("Машина в точке %d %d", point.x, point.y);
        }
    }
}
