package ru.job4j.ood.tdd.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator, (num -> num > 0));
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator, (num -> num < 0));
    }

    private <T> T getValue(List<T> value, Comparator<T> comparator, IntPredicate predicate) {
        T rsl = value.isEmpty() ? null : value.get(0);
        for (T item : value) {
            if (predicate.test(comparator.compare(item, rsl))) {
                rsl = item;
            }
        }
        return rsl;
    }
}