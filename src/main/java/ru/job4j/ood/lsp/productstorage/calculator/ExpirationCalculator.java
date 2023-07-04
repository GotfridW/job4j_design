package ru.job4j.ood.lsp.productstorage.calculator;

import ru.job4j.ood.lsp.productstorage.model.Food;

public abstract class ExpirationCalculator {
    public abstract float calculate(Food product);
}
