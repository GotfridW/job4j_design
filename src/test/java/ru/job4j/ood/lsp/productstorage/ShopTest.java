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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    void add() {
        Store shop = new Shop();
        LocalDateTime now = LocalDateTime.now();
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
        List<Food> productList = List.of(
                productExpBelow25, productExp50, productExpOver75, productExpLastDay, productExpTotally
        );
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        for (Food product : productList) {
            shop.add(calculator.calculate(product, now));
        }
        assertThat(shop.getStock()).contains(productExp50, productExpOver75, productExpLastDay);
        assertThat(productExpOver75.getPrice()).isEqualTo(58.5);
    }

}