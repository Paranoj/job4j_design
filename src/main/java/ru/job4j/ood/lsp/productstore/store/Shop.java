package ru.job4j.ood.lsp.productstore.store;

import ru.job4j.ood.lsp.productstore.ExpDateAssessor;
import ru.job4j.ood.lsp.productstore.food.Food;

public class Shop extends AbstractStore {

    private final static double LOWER_THRESHOLD = 0.25D;

    private final static double UPPER_THRESHOLD = 0.75D;

    private static final double TRASH_THRESHOLD = 1D;

    @Override
    boolean inCondition(Food food) {
        var rsl = false;
        var value = new ExpDateAssessor().expDateAssessor(food.getCreateDate(), food.getExpiryDate());
        if (value >= LOWER_THRESHOLD && value < UPPER_THRESHOLD) {
            rsl = true;
        }
        if (value >= UPPER_THRESHOLD && value <= TRASH_THRESHOLD) {
            food.setPrice(priceWithDiscount(food));
            rsl = true;
        }
        return rsl;
    }

    private static double priceWithDiscount(Food food) {
        return food.getPrice() * (1 - food.getDiscount());
    }
}
