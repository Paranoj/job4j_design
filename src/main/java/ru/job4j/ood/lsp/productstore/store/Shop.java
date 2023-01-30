package ru.job4j.ood.lsp.productstore.store;

import ru.job4j.ood.lsp.productstore.ExpirationCalculator;
import ru.job4j.ood.lsp.productstore.food.Food;

import java.time.LocalDate;

public class Shop extends AbstractStore {

    private final static double UPPER_THRESHOLD = 75D;

    private final ExpirationCalculator<LocalDate> expirationCalculator;

    public Shop(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean inCondition(Food food) {
        var rsl = false;
        var value = expirationCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        if (value >= Warehouse.LOWER_THRESHOLD && value <= Trash.TRASH_THRESHOLD) {
            if (value > UPPER_THRESHOLD) {
                food.setPrice(priceWithDiscount(food));
            }
            rsl = true;
        }
        return rsl;
    }

    private static double priceWithDiscount(Food food) {
        return food.getPrice() * ((100 - food.getDiscount()) / 100);
    }
}
