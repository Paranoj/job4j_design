package ru.job4j.ood.lsp.productstore.store;

import ru.job4j.ood.lsp.productstore.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean move(Food food) {
        var rsl = false;
        if (inCondition(food)) {
            foodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(foodList);
    }

    protected abstract boolean inCondition(Food food);
}
