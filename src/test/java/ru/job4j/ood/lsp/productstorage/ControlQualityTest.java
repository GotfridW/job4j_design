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
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash), calculator);
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
        List<Food> productList = List.of(productExp0, productExpBelow25, productExp50,
                productExpOver75, productExpLastDay, productExpTotally);
        for (Food product : productList) {
            controlQuality.shipOut(product, now);
        }

        assertThat(warehouse.getStock()).contains(productExp0, productExpBelow25);

        assertThat(shop.getStock()).contains(productExp50, productExpOver75, productExpLastDay);
        assertThat(productExpOver75.getPrice()).isEqualTo(58.5);

        assertThat(trash.getStock()).contains(productExpTotally);
    }
}