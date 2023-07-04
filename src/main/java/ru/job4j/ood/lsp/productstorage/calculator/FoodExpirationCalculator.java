package ru.job4j.ood.lsp.productstorage.calculator;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class FoodExpirationCalculator extends ExpirationCalculator {
    private final static LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private void validate(LocalDateTime createDate, LocalDateTime expiryDate) {
        if (HOURS.between(createDate, CURRENT_DATE) < 0 || HOURS.between(createDate, expiryDate) < 0) {
            throw new IllegalArgumentException("Invalid date properties!");
        }
    }
    @Override
    public float calculate(Food product) {
        LocalDateTime createDate = product.getCreateDate();
        LocalDateTime expiryDate = product.getExpiryDate();
        validate(createDate, expiryDate);
        float shelfLife = (float) HOURS.between(createDate, expiryDate);
        float productsAge = (float) HOURS.between(createDate, CURRENT_DATE);
        return (productsAge / shelfLife) * 100;
    }
}
