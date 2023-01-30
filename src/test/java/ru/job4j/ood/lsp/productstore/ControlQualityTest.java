package ru.job4j.ood.lsp.productstore;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstore.food.Bread;
import ru.job4j.ood.lsp.productstore.food.Cookies;
import ru.job4j.ood.lsp.productstore.food.Food;
import ru.job4j.ood.lsp.productstore.store.Shop;
import ru.job4j.ood.lsp.productstore.store.Store;
import ru.job4j.ood.lsp.productstore.store.Trash;
import ru.job4j.ood.lsp.productstore.store.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    private final Store warehouse = new Warehouse(new LocalDateExpirationCalculator());
    private final Store shop = new Shop(new LocalDateExpirationCalculator());
    private final Store trash = new Trash(new LocalDateExpirationCalculator());
    private final ControlQuality controlQuality = new ControlQuality(List.of(
            warehouse, shop, trash));

    @Test
    void whenDistributionToWarehouse() {
        Food cookies = new Cookies("Original cookies", LocalDate.now(),
                LocalDate.now().plusMonths(6), 77, 40);
        controlQuality.distribution(cookies);
        assertThat(warehouse.getAll()).isEqualTo(List.of(cookies));
    }

    @Test
    void whenDistributionToShop() {
        Food cookies = new Cookies("Original cookies", LocalDate.now().minusMonths(2),
                LocalDate.now().plusMonths(6), 77, 40);
        controlQuality.distribution(cookies);
        assertThat(shop.getAll()).isEqualTo(List.of(cookies));
    }

    @Test
    void whenDistributionToTrash() {
        Food cookies = new Cookies("Original cookies", LocalDate.now().minusMonths(6).plusDays(5),
                LocalDate.now().minusMonths(1), 77, 40);
        controlQuality.distribution(cookies);
        assertThat(trash.getAll()).isEqualTo(List.of(cookies));
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

    @Test
    void whenDynamicRedistribution() {
        Food cookies = new Cookies("Original cookies", LocalDate.now().minusMonths(2),
                LocalDate.now().plusMonths(4), 77, 40);
        controlQuality.distribution(cookies);
        Food bread = new Bread("Borodinskiy", LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(7), 45, 40);
        controlQuality.distribution(bread);
        controlQuality.resort();
        assertThat(shop.getAll()).isEqualTo(List.of(cookies, bread));
    }
}
