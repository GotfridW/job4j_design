package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.util.List;

public interface Store {
    boolean add(Food product);
    List<Food> getStock();
    void clear();
}
