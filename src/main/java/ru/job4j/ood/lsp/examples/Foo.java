package ru.job4j.ood.lsp.examples;

public class Foo {

    public void doSmth(int num) {
        if (num <= 0 || num > 5) {
            throw new IllegalStateException("Input out of range 1-5");
        }
    }
}
