package ru.job4j.ood.lsp.productstore.food;

import java.time.LocalDate;

public class Cookies extends Food {

    public Cookies(String name, LocalDate createDate, LocalDate expiryDate, double price, double discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
