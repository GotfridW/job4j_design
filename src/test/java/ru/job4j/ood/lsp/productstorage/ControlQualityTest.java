package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.calculator.FoodExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Bacon;
import ru.job4j.ood.lsp.productstorage.model.Eggs;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.model.Milk;
import ru.job4j.ood.lsp.productstorage.store.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    static public ExpirationCalculator calculator;
    static public Store warehouse;
    static public Store shop;
    static public Store trash;
    static public Store distributor;
    static public ControlQuality controlQuality;

    @BeforeAll
    static void setUp() {
        calculator = new FoodExpirationCalculator();
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        distributor = new DistributeStore();
        controlQuality = new ControlQuality(List.of(
                warehouse, shop, trash), distributor, calculator);
    }

    @Test
    void distribute() {
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

    @Test
    void whenResortThenAllProductsInPlaceAndNothingLeft() {
        List<Food> warehouseProducts = warehouse.getStock();
        List<Food> shopProducts = shop.getStock();
        List<Food> trashProducts = trash.getStock();
        LocalDateTime now = LocalDateTime.now();
        controlQuality.resort(now);

        assertThat(warehouse.getStock()).isEqualTo(warehouseProducts);
        assertThat(shop.getStock()).isEqualTo(shopProducts);
        assertThat(trash.getStock()).isEqualTo(trashProducts);

        assertThat(distributor.getStock()).isEmpty();
    }
}