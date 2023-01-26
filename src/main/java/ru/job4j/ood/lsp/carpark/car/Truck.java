package ru.job4j.ood.lsp.carpark.car;

public class Truck extends Vehicle {

    public Truck(String name, int size) {
        super(name, size);
        if (size <= 1) {
            throw new IllegalArgumentException("Truck size has to be more than 1.");
        }
    }
}
