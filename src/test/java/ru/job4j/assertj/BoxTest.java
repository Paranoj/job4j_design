package ru.job4j.assertj;

import jdk.jfr.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void whenVertexNumberIsPositiveAndGreaterThan4() {
        Box box = new Box(8, 8);
        int number = box.getNumberOfVertices();
        assertThat(number).isPositive()
                .isGreaterThan(4);
    }

    @Test
    void whenEdgesAreZero() {
        Box box = new Box(0, 0);
        int number = box.getNumberOfVertices();
        assertThat(number).isLessThan(0)
                .isEqualTo(-1);
    }

    @Test
    void whenFigureExist() {
        Box box = new Box(4, 4);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void whenFigureNotExist() {
        Box box = new Box(10, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();

    }

    @Test
    void whenCalculatingAreaForCube() {
        Box box = new Box(8, 8);
        double result = box.getArea();
        assertThat(result).isPositive()
                .isGreaterThan(380.0d)
                .isEqualTo(384.0d, withPrecision(0.0001d));
    }

    @Test
    void whenCalculatingAreaForSphere() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.63d, withPrecision(0.01d));
    }
}
