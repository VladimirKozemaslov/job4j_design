package ru.job4j.ood.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator, (num -> num > 0));
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator, (num -> num < 0));
    }

    private <T> T getValue(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T rsl;
        switch (value.size()) {
            case 0:
                rsl = null;
                break;
            case 1:
                rsl = value.get(0);
                break;
            default:
                rsl = value.get(0);
                for (int i = 1; i < value.size(); i++) {
                    T item = value.get(i);
                    if (!predicate.test(comparator.compare(rsl, item))) {
                        rsl = item;
                    }
                }
                break;
        }
        return rsl;
    }
}