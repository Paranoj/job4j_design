package ru.job4j.collection;

import java.util.Objects;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    public boolean isEmpty() {
        return linked.getSize() == 0;
    }

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
