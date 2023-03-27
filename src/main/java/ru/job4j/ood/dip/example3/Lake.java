package ru.job4j.ood.dip.example3;

import ru.job4j.ood.dip.example2.Fish;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Lake implements Water {
    private Map<Fish, Integer> lakeFishes = new HashMap<>();

    public boolean saveData(File file) {
        return true;
    }

    @Override
    public File load() {
        return null;
    }
}
