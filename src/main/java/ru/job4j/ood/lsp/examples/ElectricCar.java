package ru.job4j.ood.lsp.examples;

public class ElectricCar implements Car {

    /* Нарушение принципа LSP - Реализация ElectricCar не может выполнить метод
    turnOnEngine(), т.к. в электромобилях не используется ДВС. */

    @Override
    public void turnOnEngine() {
        throw new AssertionError("Only electric motor available for this type of cars.");
    }

    @Override
    public int accelerate() {
        return 100;
    }
}
