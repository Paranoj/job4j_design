package ru.job4j.ood.lsp.productstore;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstore.food.Cookies;
import ru.job4j.ood.lsp.productstore.food.Food;
import ru.job4j.ood.lsp.productstore.store.Shop;
import ru.job4j.ood.lsp.productstore.store.Trash;
import ru.job4j.ood.lsp.productstore.store.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    private final ControlQuality controlQuality = new ControlQuality(List.of(
            new Shop(), new Trash(), new Warehouse()
    ));

    @Test
    void whenDistributionToWarehouse() {
        Food cookies = new Cookies("Original cookies", LocalDate.of(2023,  1, 20),
                LocalDate.of(2023, 7, 20), 77, 0.4);
        var store = controlQuality.distribution(cookies);
        assertThat(store).isInstanceOf(Warehouse.class);
    }

    @Test
    void whenDistributionToShop() {
        Food cookies = new Cookies("Original cookies", LocalDate.of(2022,  12, 1),
                LocalDate.of(2023, 6, 1), 77, 0.4);
        var store = controlQuality.distribution(cookies);
        assertThat(store).isInstanceOf(Shop.class);
    }

    @Test
    void whenDistributionToTrash() {
        Food cookies = new Cookies("Original cookies", LocalDate.of(2022, 6, 1),
                LocalDate.of(2023, 1, 1), 77, 0.4);
        var store = controlQuality.distribution(cookies);
        assertThat(store).isInstanceOf(Trash.class);
    }

    @Test
    void whenDistributionToShopWithDiscount() {
        Food cookies = new Cookies("Original cookies", LocalDate.of(2022,   8, 5),
                LocalDate.of(2023, 2, 5), 77, 0.4);
        var store = controlQuality.distribution(cookies);
        var list = store.getAll();
        assertThat(list.get(0).getPrice()).isEqualTo(46.2D, offset(0.01D));
    }

    @Test
    void whenDistributionToShopWithoutDiscount() {
        Food cookies = new Cookies("Original cookies", LocalDate.of(2022, 12, 5),
                LocalDate.of(2023, 6, 5), 77, 0.4);
        var store = controlQuality.distribution(cookies);
        var list = store.getAll();
        assertThat(list.get(0).getPrice()).isEqualTo(77D, offset(0.01D));
    }
}
