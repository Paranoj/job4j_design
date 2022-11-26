package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean availability;

    @XmlAttribute
    private double price;

    @XmlAttribute
    private String brand;

    private Model model;

    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Car() { }

    public Car(boolean availability, double price, String brand, Model model, String[] statuses) {
        this.availability = availability;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.statuses = statuses;
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
