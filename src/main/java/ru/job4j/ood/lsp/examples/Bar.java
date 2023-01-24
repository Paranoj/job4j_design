package ru.job4j.ood.lsp.examples;

public class Bar extends Foo {

    /* Нарушение LSP. Наследник усиливает предусловие, обозначенное родителем.*/

    @Override
    public void doSmth(int num) {
        if (num <= 0 || num > 3) {
            throw new IllegalStateException("Input out of range 1-10");
        }
    }
}
