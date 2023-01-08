package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator.reversed());
    }

    public <T> T findBy(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        int rsl;
        T t = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            rsl = comparator.compare(t, value.get(i));
            if (rsl < 0) {
                t = value.get(i);
            }
        }
        return t;
    }
}
