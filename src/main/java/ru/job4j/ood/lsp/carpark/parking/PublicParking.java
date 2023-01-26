package ru.job4j.ood.lsp.carpark.parking;

import ru.job4j.ood.lsp.carpark.car.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class PublicParking implements Parking {

    private int sizeCar;
    private int sizeTruck;
    private final List<Vehicle> vehicleList = new ArrayList<>();

    public PublicParking(int sizeCar, int sizeTruck) {
        this.sizeCar = sizeCar;
        this.sizeTruck = sizeTruck;
        if (sizeCar < 0 || sizeTruck < 0) {
            throw new IllegalArgumentException("Unreachable parameters.");
        }
    }

    @Override
    public boolean add(Vehicle vehicle) {
        var rsl = false;
        if (sizeTruck >= 1 && vehicle.getSize() > 1) {
            vehicleList.add(vehicle);
            sizeTruck--;
            rsl = true;
        } else if (sizeCar >= vehicle.getSize()) {
            vehicleList.add(vehicle);
            sizeCar -= vehicle.getSize();
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleList;
    }
}
