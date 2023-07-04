package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> stock = new ArrayList<>();

    protected abstract boolean complies(Food product);

    @Override
    public boolean add(Food product) {
        boolean result = false;
        if (complies(product)) {
            stock.add(product);
            result = true;
        }
        return result;
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
