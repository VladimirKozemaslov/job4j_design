package ru.job4j.ood.lsp.example2;

public class DumpTruck extends Transport {
    public DumpTruck(int maxLoad) {
        super(maxLoad);
    }

    @Override
    public void load(int weight) {
        currentLoad += weight;
    }
}
