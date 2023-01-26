package ru.job4j.ood.lsp.carpark.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.carpark.car.Car;
import ru.job4j.ood.lsp.carpark.car.Truck;
import ru.job4j.ood.lsp.carpark.car.Vehicle;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PublicParkingTest {

    @Test
    void whenCreateTruckWithSizeLessTwo() {
        assertThatThrownBy(() -> new Truck(
                "MAN", 1)).hasMessageMatching("Truck size has to be more than 1.");
    }

    @Test
    void whenCreateParkingWithSizeLessThanZero() {
        assertThatThrownBy(() -> new PublicParking(
                -1, 1)).hasMessageMatching("Unreachable parameters.");
    }

    @Test
    void whenNotEnoughSpaceForParkingOnCarSpots() {
        Vehicle truck = new Truck("MAN", 3);
        Vehicle bmw = new Car("BMW");
        Vehicle toyota = new Car("Toyota");
        Parking parking = new PublicParking(3, 0);
        parking.add(bmw);
        parking.add(toyota);
        assertThat(parking.add(truck)).isFalse();
    }

    @Test
    void whenEnoughSpaceForParkingOnCarSpots() {
        Vehicle truck = new Truck("MAN", 3);
        Vehicle bmw = new Car("BMW");
        Parking parking = new PublicParking(4, 0);
        parking.add(truck);
        parking.add(bmw);
        assertThat(parking.getAll()).isEqualTo(List.of(truck, bmw));
    }

    @Test
    void whenNotEnoughSpaceForParkingOnBothSpots() {
        Vehicle truck = new Truck("MAN", 3);
        Vehicle bmw = new Car("BMW");
        Vehicle toyota = new Car("Toyota");
        Parking parking = new PublicParking(1, 1);
        parking.add(truck);
        parking.add(toyota);
        assertThat(parking.add(bmw)).isFalse();
    }

    @Test
    void whenEnoughSpaceForParkingOnBothSpots() {
        Vehicle truck = new Truck("MAN", 3);
        Vehicle bmw = new Car("BMW");
        Vehicle toyota = new Car("Toyota");
        Parking parking = new PublicParking(2, 1);
        parking.add(truck);
        parking.add(toyota);
        assertThat(parking.add(bmw)).isTrue();
    }
}
