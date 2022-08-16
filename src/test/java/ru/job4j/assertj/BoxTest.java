package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenNumberOfVerticesEquals4() {
        Box box = new Box(4, 8);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(4);
    }

    @Test
    void whenNumberOfVerticesEquals10() {
        Box box = new Box(12, 8);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(-1);
    }

    @Test
    void isFigureExists() {
        Box box = new Box(6, 6);
        boolean exists = box.isExist();
        assertThat(exists).isTrue();
    }

    @Test
    void isFigureExists2() {
        Box box = new Box(7, 6);
        boolean exists = box.isExist();
        assertThat(exists).isFalse();
    }

    @Test
    void whenAreaEquals50p26() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(50.26d, withPrecision(0.006d));
    }

    @Test
    void whenAreaEquals15p58() {
        Box box = new Box(4, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(15.58d, withPrecision(0.009d));
    }
}