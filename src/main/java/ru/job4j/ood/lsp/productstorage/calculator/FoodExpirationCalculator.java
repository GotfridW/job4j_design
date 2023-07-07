package ru.job4j.ood.lsp.productstorage.calculator;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class FoodExpirationCalculator extends ExpirationCalculator {

    @Override
    public Food calculate(Food product, LocalDateTime targetDate) {
        LocalDateTime createDate = product.getCreateDate();
        LocalDateTime expiryDate = product.getExpiryDate();
        float shelfLife = (float) HOURS.between(createDate, expiryDate);
        float productsAge = (float) HOURS.between(createDate, targetDate);
        if (shelfLife < 0 || productsAge < 0) {
            throw new IllegalArgumentException("Invalid date properties!");
        }
        product.setExpiryLevel((productsAge / shelfLife) * 100);
        return product;
    }
}
