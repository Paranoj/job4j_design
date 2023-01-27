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
            new Shop(new LocalDateExpirationCalculator()), new Trash(new LocalDateExpirationCalculator()),
            new Warehouse(new LocalDateExpirationCalculator())
    ));

    @Test
    void whenDistributionToWarehouse() {
        Food cookies = new Cookies("Original cookies", LocalDate.now(),
                LocalDate.now().plusMonths(6), 77, 40);
        var store = controlQuality.distribution(cookies);
        assertThat(store).isInstanceOf(Warehouse.class);
    }

    @Test
    void whenDistributionToShop() {
        Food cookies = new Cookies("Original cookies", LocalDate.now().minusMonths(2),
                LocalDate.now().plusMonths(6), 77, 40);
        var store = controlQuality.distribution(cookies);
        assertThat(store).isInstanceOf(Shop.class);
    }

    @Test
    void whenDistributionToTrash() {
        Food cookies = new Cookies("Original cookies", LocalDate.now().minusMonths(6).plusDays(5),
                LocalDate.now().minusMonths(1), 77, 40);
        var store = controlQuality.distribution(cookies);
        assertThat(store).isInstanceOf(Trash.class);
    }

    @Test
    void whenDistributionToShopWithDiscount() {
        Food cookies = new Cookies("Original cookies", LocalDate.now().minusMonths(4),
                LocalDate.now().plusDays(15), 77, 40);
        var store = controlQuality.distribution(cookies);
        var list = store.getAll();
        assertThat(list.get(0).getPrice()).isEqualTo(46.2D, offset(0.01D));
    }

    @Test
    void whenDistributionToShopWithoutDiscount() {
        Food cookies = new Cookies("Original cookies", LocalDate.now().minusMonths(2),
                LocalDate.now().plusMonths(4), 77, 40);
        var store = controlQuality.distribution(cookies);
        var list = store.getAll();
        assertThat(list.get(0).getPrice()).isEqualTo(77D, offset(0.01D));
    }
}
