package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.productstorage.model.Food;

public class Shop extends AbstractStore {
    private static final int MIN_EXPIRY_THRESHOLD = 25;
    private static final int MAX_EXPIRY_THRESHOLD = 100;
    private static final int DISCOUNT_EXPIRY_THRESHOLD = 75;
    private final ExpirationCalculator expirationCalculator;
    private float calculatedExpiryRate;

    public Shop(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean complies(Food product) {
        boolean result = false;
        calculatedExpiryRate = expirationCalculator.calculate(product);
        if (calculatedExpiryRate > MIN_EXPIRY_THRESHOLD && calculatedExpiryRate <= MAX_EXPIRY_THRESHOLD) {
            result = true;
        }
        return result;
    }

    private void applyDiscount(Food product) {
        if (calculatedExpiryRate > DISCOUNT_EXPIRY_THRESHOLD) {
            double price = product.getPrice();
            product.setPrice(price - ((price / 100) * product.getDiscount()));
        }
    }

    @Override
    public boolean add(Food product) {
        boolean result = false;
        if (super.add(product)) {
            applyDiscount(product);
            result = true;
        }
        return result;
    }
}