package ru.job4j.ood.lsp.productstorage;

import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.store.Store;

import java.time.LocalDateTime;
import java.util.List;

public class ControlQuality {
    private final List<Store> storeList;
    private final ExpirationCalculator calculator;

    public ControlQuality(List<Store> storeList, ExpirationCalculator calculator) {
        this.storeList = storeList;
        this.calculator = calculator;
    }

    public void shipOut(Food product, LocalDateTime targetDate) {
        calculator.calculate(product, targetDate);
        for (Store store : storeList) {
            if (store.add(calculator.calculate(product, targetDate))) {
                break;
            }
        }
    }
}
