package ru.job4j.ood.lsp.examples;

public class MotorCar implements Car {

    @Override
    public void turnOnEngine() {
        System.out.println("Engine on!");
    }

    @Override
    public int accelerate() {
        return 50;
    }
}
