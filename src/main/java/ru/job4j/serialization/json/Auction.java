package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Auction {
    public static void main(String[] args) {
        final Car car = new Car(true, 20000, "Skoda",
                new Model("Octavia RS"), new String[] {"Shipping soon", "Shipped", "Out for delivery", "Delivered"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
        "{"
                + "\"availability\":false,"
                + "\"price\":10000.0,"
                + "\"brand\":Toyota,"
                + "\"model\":"
                + "{"
                + "\"mod\":\"Corolla\""
                + "},"
                + "\"statuses\":"
                + "[\"Shipping soon\"]"
                + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
