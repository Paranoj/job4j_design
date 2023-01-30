package ru.job4j.ood.lsp.productstore.store;

import ru.job4j.ood.lsp.productstore.ExpirationCalculator;
import ru.job4j.ood.lsp.productstore.food.Food;

import java.time.LocalDate;

public class Trash extends AbstractStore {

    public static final double TRASH_THRESHOLD = 100D;
    private final ExpirationCalculator<LocalDate> expirationCalculator;

    public Trash(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean inCondition(Food food) {
        var value = expirationCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return value > TRASH_THRESHOLD;
    }
}
