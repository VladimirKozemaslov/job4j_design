package ru.job4j.ood.lsp.example1;

public class ComfortClassTaxi extends Taxi {
    public ComfortClassTaxi(int minPrice) {
        super(minPrice);
        if (minPrice < 500) {
            throw new IllegalArgumentException("Minimal price must be more than 500.");
        }
    }
}
