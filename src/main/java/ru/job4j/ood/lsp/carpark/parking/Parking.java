package ru.job4j.ood.lsp.carpark.parking;

import ru.job4j.ood.lsp.carpark.car.Vehicle;

import java.util.List;

public interface Parking {

    boolean add(Vehicle vehicle);

    List<Vehicle> getCars();

    List<Vehicle> getTrucks();
}
