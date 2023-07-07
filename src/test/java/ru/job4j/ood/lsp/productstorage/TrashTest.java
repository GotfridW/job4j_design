package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.calculator.FoodExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Bacon;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.model.Milk;
import ru.job4j.ood.lsp.productstorage.store.Store;
import ru.job4j.ood.lsp.productstorage.store.Trash;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    void add() {
        Store trash = new Trash();
        LocalDateTime now = LocalDateTime.now();
        Food productExp50 = new Bacon(
                "Bacon", now.minusDays(5), now.plusDays(5), 250, 50);
        Food productExpLastDay = new Milk(
                "Milk", now.minusDays(7), now, 70, 30);
        Food productExpTotally = new Bacon(
                "Bacon", now.minusDays(15), now.minusDays(2), 250, 50);
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        List<Food> productList = List.of(productExp50, productExpLastDay, productExpTotally);
        for (Food product : productList) {
            trash.add(calculator.calculate(product, now));
        }
        assertThat(trash.getStock()).hasSize(1)
                .contains(productExpTotally);
    }
}