package ru.job4j.ood.lsp.productstore.store;

import ru.job4j.ood.lsp.productstore.ExpDateAssessor;
import ru.job4j.ood.lsp.productstore.food.Food;

public class Trash extends AbstractStore {

    private static final double THRESHOLD = 1D;

    @Override
    boolean inCondition(Food food) {
        var value = new ExpDateAssessor().expDateAssessor(food.getCreateDate(), food.getExpiryDate());
        return value > THRESHOLD;
    }
}
