package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.model.Food;

public class Warehouse extends AbstractStore {

    @Override
    protected boolean complies(Food product) {
        return product.getExpiryLevel() <= WAREHOUSE_THRESHOLD;
    }
}
