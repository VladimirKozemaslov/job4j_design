package ru.job4j.ood.dip.example2;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Lake {
    private Map<Fish, Integer> lakeFishes = new HashMap<>();

    public boolean saveData(File file) {
        return true;
    }
}
