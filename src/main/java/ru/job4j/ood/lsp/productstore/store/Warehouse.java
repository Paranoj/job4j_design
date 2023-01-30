package ru.job4j.ood.lsp.productstore.store;

import ru.job4j.ood.lsp.productstore.ExpirationCalculator;
import ru.job4j.ood.lsp.productstore.food.Food;

import java.time.LocalDate;

public class Warehouse extends AbstractStore {

    public static final double LOWER_THRESHOLD = 25D;

    private final ExpirationCalculator<LocalDate> expirationCalculator;

    public Warehouse(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean inCondition(Food food) {
        var value = expirationCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return value < LOWER_THRESHOLD;
    }
}
