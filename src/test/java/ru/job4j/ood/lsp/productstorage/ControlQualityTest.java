package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.calculator.FoodExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Bacon;
import ru.job4j.ood.lsp.productstorage.model.Eggs;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.model.Milk;
import ru.job4j.ood.lsp.productstorage.store.Shop;
import ru.job4j.ood.lsp.productstorage.store.Store;
import ru.job4j.ood.lsp.productstorage.store.Trash;
import ru.job4j.ood.lsp.productstorage.store.Warehouse;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    @Test
    void distribute() {
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        Store warehouse = new Warehouse(calculator);
        Store shop = new Shop(calculator);
        Store trash = new Trash(calculator);
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        LocalDateTime now = LocalDateTime.now();
        Food productExp0 = new Eggs(
                "Eggs", now, now.plusDays(20), 65, 10);
        Food productExpBelow25 = new Milk(
                "Milk", now.minusDays(2), now.plusDays(10), 70, 30);
        Food productExp50 = new Bacon(
                "Bacon", now.minusDays(5), now.plusDays(5), 250, 50);
        Food productExpOver75 = new Eggs(
                "Eggs", now.minusDays(10), now.plusDays(3), 65, 10);
        Food productExpLastDay = new Milk(
                "Milk", now.minusDays(7), now, 70, 30);
        Food productExpTotally = new Bacon(
                "Bacon", now.minusDays(15), now.minusDays(2), 250, 50);
        controlQuality.shipOut(productExp0);
        controlQuality.shipOut(productExpBelow25);
        controlQuality.shipOut(productExp50);
        controlQuality.shipOut(productExpOver75);
        controlQuality.shipOut(productExpLastDay);
        controlQuality.shipOut(productExpTotally);

        List<Food> expected = List.of(productExp0, productExpBelow25);
        assertThat(warehouse.getStock()).isEqualTo(expected);

        expected = List.of(productExp50, productExpOver75, productExpLastDay);
        assertThat(shop.getStock()).isEqualTo(expected);
        assertThat(productExpOver75.getPrice()).isEqualTo(58.5);

        expected = List.of(productExpTotally);
        assertThat(trash.getStock()).isEqualTo(expected);
    }
}