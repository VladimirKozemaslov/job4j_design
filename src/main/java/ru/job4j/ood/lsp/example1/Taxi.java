package ru.job4j.ood.lsp.example1;

public class Taxi {
    private int minPrice;

    public Taxi(int minPrice) {
        if (minPrice >= 0) {
            this.minPrice = minPrice;
        } else {
            throw new IllegalArgumentException("Minimal price must be more than 0.");
        }
    }
}
