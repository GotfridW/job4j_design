package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Food;

public class Trash extends AbstractStore {
    private static final int MAX_EXPIRY_THRESHOLD = 100;
    private final ExpirationCalculator expirationCalculator;

    public Trash(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean complies(Food product) {
        float calculatedExpiryRate = expirationCalculator.calculate(product);
        return calculatedExpiryRate > MAX_EXPIRY_THRESHOLD;
    }
}
