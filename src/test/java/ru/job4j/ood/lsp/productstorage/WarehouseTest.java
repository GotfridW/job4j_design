package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.calculator.FoodExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Bacon;
import ru.job4j.ood.lsp.productstorage.model.Eggs;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.model.Milk;
import ru.job4j.ood.lsp.productstorage.store.Store;
import ru.job4j.ood.lsp.productstorage.store.Warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

class WarehouseTest {
    @Test
    void add() {
        Store warehouse = new Warehouse();
        LocalDateTime now = LocalDateTime.now();
        Food productExp0 = new Bacon("Bacon", now, now.plusDays(20), 250, 50);
        Food productExpUnder25 = new Milk(
                "Milk", now.minusDays(2), now.plusDays(10), 70, 30);
        Food productExpOver25 = new Eggs("Eggs", now.minusDays(10), now.plusDays(10), 65, 10);
        Food productExpOver100 = new Bacon(
                "Bacon", now.minusDays(15), now.minusDays(2), 250, 50);
        List<Food> productList = List.of(productExp0, productExpUnder25, productExpOver25, productExpOver100);
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        for (Food product : productList) {
            warehouse.add(calculator.calculate(product, now));
        }

        assertThat(warehouse.getStock()).contains(productExp0, productExpUnder25);




    }

}