package ru.job4j.ood.lsp.example2;

public class DumpTruck extends Transport {
    public DumpTruck(int maxLoad) {
        super(maxLoad);
    }

    @Override
    public int load(int weight) {
        currentLoad += weight;
        return currentLoad;
    }
}
