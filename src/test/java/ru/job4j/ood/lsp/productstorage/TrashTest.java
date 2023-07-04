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

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    void add() {
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        Store trash = new Trash(calculator);
        LocalDateTime now = LocalDateTime.now();
        Food productExp50 = new Bacon(
                "Bacon", now.minusDays(5), now.plusDays(5), 250, 50);
        Food productExpLastDay = new Milk(
                "Milk", now.minusDays(7), now, 70, 30);
        Food productExpTotally = new Bacon(
                "Bacon", now.minusDays(15), now.minusDays(2), 250, 50);
        trash.add(productExp50);
        trash.add(productExpLastDay);
        trash.add(productExpTotally);
        assertThat(trash.getStock()).hasSize(1)
                .contains(productExpTotally);
    }
}