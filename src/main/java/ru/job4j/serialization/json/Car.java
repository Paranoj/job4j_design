package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean availability;
    private final double price;
    private final String brand;
    private final Model model;
    private final  String[] statuses;

    public Car(boolean availability, double price, String brand, Model model, String[] statuses) {
        this.availability = availability;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.statuses = statuses;
    }

    public boolean isAvailability() {
        return availability;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public Model getModel() {
        return model;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Car{"
                + "availability=" + availability
                + ", price=" + price
                + ", brand='" + brand + '\''
                + ", model=" + model
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
