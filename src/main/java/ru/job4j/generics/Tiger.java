package ru.job4j.generics;

public class Tiger extends Predator {
    private String color;

    public Tiger(String color) {
        super("Tiger", "Fangs");
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Tiger{"
                + "color='" + color + '\''
                + '}';
    }
}
