package ru.job4j.ood.lsp.carpark.parking;

import ru.job4j.ood.lsp.carpark.car.Car;
import ru.job4j.ood.lsp.carpark.car.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class PublicParking implements Parking {

    private int parkingCarSize;
    private int parkingTruckSize;
    private final List<Vehicle> carList = new ArrayList<>();
    private final List<Vehicle> truckList = new ArrayList<>();

    public PublicParking(int parkingCarSize, int parkingTruckSize) {
        this.parkingCarSize = parkingCarSize;
        this.parkingTruckSize = parkingTruckSize;
        if (parkingCarSize < 0 || parkingTruckSize < 0) {
            throw new IllegalArgumentException("Unreachable parameters.");
        }
    }

    @Override
    public boolean add(Vehicle vehicle) {
        var rsl = false;
        if (parkingTruckSize >= Car.SIZE && vehicle.getSize() > Car.SIZE) {
            truckList.add(vehicle);
            parkingTruckSize--;
            rsl = true;
        } else if (parkingCarSize >= vehicle.getSize()) {
            carList.add(vehicle);
            parkingCarSize -= vehicle.getSize();
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Vehicle> getCars() {
        return List.copyOf(carList);
    }

    @Override
    public List<Vehicle> getTrucks() {
        return List.copyOf(truckList);
    }
}
