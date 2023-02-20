package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.kiss.MaxMin;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxMinTest {
    @Test
    void whenMaxIntegerEquals13() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1, 5, 8, 13, -6, 11);
        assertThat(maxMin.max(list, Integer::compare)).isEqualTo(13);
    }

    @Test
    void whenMInIntegerEquals13() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1, 5, 8, 13, -6, 11);
        assertThat(maxMin.min(list, Integer::compare)).isEqualTo(-6);
    }

    @Test
    void whenNoElementsThenNull() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = new ArrayList<>();
        assertThat(maxMin.min(list, Integer::compare)).isNull();
    }

    @Test
    void whenOneElementThenElement() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(11);
        assertThat(maxMin.min(list, Integer::compare)).isEqualTo(11);
    }

    @Test
    void whenTwoMinValuesExists() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1, 5, -6, 8, 13, -6, 11);
        assertThat(maxMin.min(list, Integer::compare)).isEqualTo(-6);
    }

    @Test
    void whenTwoMaxValuesExists() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(1, 5, 8, 13, -6, 13, 11);
        assertThat(maxMin.max(list, Integer::compare)).isEqualTo(13);
    }
}