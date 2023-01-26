package ru.job4j.ood.lsp.productstore.food;

import ru.job4j.ood.lsp.productstore.food.Food;

import java.time.LocalDate;

public class Milk extends Food {

    public Milk(String name, LocalDate createDate, LocalDate expiryDate, double price, double discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
