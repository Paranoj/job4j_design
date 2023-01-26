package ru.job4j.ood.lsp.productstore.store;

import ru.job4j.ood.lsp.productstore.food.Food;

import java.util.List;

public interface Store {

    boolean move(Food food);

    List<Food> getAll();
}
