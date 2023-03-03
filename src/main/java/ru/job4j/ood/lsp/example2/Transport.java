package ru.job4j.ood.lsp.example2;

public class Transport {
    protected int maxLoad;
    protected int currentLoad;

    public Transport(int maxLoad) {
        this.maxLoad = maxLoad;
        this.currentLoad = 0;
    }

    public void load(int weight) {
        if (maxLoad <= currentLoad + weight) {
            currentLoad += weight;
        } else {
            throw new IllegalArgumentException("Exceeding the maximum load.");
        }
    }
}
