package ru.job4j.ood.dip.example;

import java.time.LocalDate;

public class Order {

    private final int id;
    private final String name;
    private final LocalDate sent;
    private final LocalDate delivered;

    public Order(int id, String name, LocalDate sent, LocalDate delivered) {
        this.id = id;
        this.name = name;
        this.sent = sent;
        this.delivered = delivered;
    }

    /* Нарушение DIP. Логирование зависит от консольного вывода.*/
    public void getLog(Order order) {
        System.out.println("Get error with order: " + id + " " + name);
    }
}
