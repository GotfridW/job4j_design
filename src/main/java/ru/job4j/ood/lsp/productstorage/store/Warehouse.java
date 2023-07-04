package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Food;

public class Warehouse extends AbstractStore {
    private static final int MAX_THRESHOLD = 25;
    private final ExpirationCalculator calculator;

    public Warehouse(ExpirationCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    protected boolean complies(Food product) {
        float calculatedExpiryRate = calculator.calculate(product);
        return calculatedExpiryRate < MAX_THRESHOLD;
    }
}
