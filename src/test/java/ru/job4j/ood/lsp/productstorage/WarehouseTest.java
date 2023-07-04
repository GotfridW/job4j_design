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
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        Store warehouse = new Warehouse(calculator);
        LocalDateTime now = LocalDateTime.now();
        Food productExpUnder25 = new Milk(
                "Milk", now.minusDays(2), now.plusDays(10), 70, 30);
        Food productExpOver25 = new Eggs("Eggs", now, now.plusDays(20), 65, 10);
        Food productExpOver100 = new Bacon(
                "Bacon", now.minusDays(15), now.minusDays(2), 250, 50);
        warehouse.add(productExpUnder25);
        warehouse.add(productExpOver25);
        warehouse.add(productExpOver100);
        List<Food> expected = List.of(productExpUnder25, productExpOver25);
        assertThat(warehouse.getStock()).isEqualTo(expected);




    }

}