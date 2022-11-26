package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Car car = new Car(true, 20000, "Skoda",
                new Model("Octavia RS"), new String[] {"Shipping soon", "Shipped", "Out for delivery", "Delivered"});

        /* JSONObject из json-строки строки */
        JSONObject jsonModel = new JSONObject("{\"model\":\"Corolla\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Shipping soon");
        list.add("Shipped");
        list.add("Out for delivery");
        list.add("Delivered");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("availability", car.isAvailability());
        jsonObject.put("price", car.getPrice());
        jsonObject.put("brand", car.getBrand());
        jsonObject.put("model", jsonModel);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car));
    }
}
