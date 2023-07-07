package ru.job4j.ood.lsp.productstorage.calculator;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.time.LocalDateTime;

public abstract class ExpirationCalculator {

    public abstract Food calculate(Food product, LocalDateTime targetDate);
}
