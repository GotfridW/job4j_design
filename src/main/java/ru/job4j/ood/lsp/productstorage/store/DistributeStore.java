package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.util.List;
import java.util.LinkedList;

public class DistributeStore implements Store {
    private final List<Food> stock = new LinkedList<>();

    @Override
    public boolean add(Food product) {
        return stock.add(product);
    }

    @Override
    public List<Food> getStock() {
        return this.stock;
    }

    @Override
    public void clear() {
        stock.clear();
    }
}
