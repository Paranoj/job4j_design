package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int modCount;

    private int size;

    private Node<E> first;

    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x.item;
        }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                while (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = node.item;
                node = node.next;
                return rsl;
            }
        };
    }
}
