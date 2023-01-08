package ru.job4j.kiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    private List<Integer> list;
    private Comparator<Integer> comparator;
    private final MaxMin maxMin = new MaxMin();

    @BeforeEach
    void list() {
        list = List.of(1, 5, 0, 2, 7, 4);
        comparator = Comparator.naturalOrder();
    }

    @Test
    void whenFindMax() {
        assertThat(maxMin.max(list, comparator)).isEqualTo(7);
    }

    @Test
    void whenFindMin() {
        assertThat(maxMin.min(list, comparator)).isEqualTo(0);
    }

    @Test
    void whenListIsEmpty() {
        list = List.of();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> maxMin.findBy(list, comparator)).withMessageMatching("List is empty");
    }
}
