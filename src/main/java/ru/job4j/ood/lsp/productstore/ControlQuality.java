package ru.job4j.ood.lsp.productstore;

import ru.job4j.ood.lsp.productstore.food.Food;
import ru.job4j.ood.lsp.productstore.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public Store distribution(Food food) {
        Store rsl = null;
        for (Store store : storeList) {
            if (store.move(food)) {
                rsl = store;
                break;
            }
        }
        return rsl;
    }

    public void resort() {
        List<Food> temp = new ArrayList<>();
        for (Store store : storeList) {
            temp.addAll(store.getAll());
            store.clearStore();
        }
        for (Food food : temp) {
            distribution(food);
        }
    }
}
