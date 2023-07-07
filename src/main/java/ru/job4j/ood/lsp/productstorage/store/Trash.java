package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.model.Food;

public class Trash extends AbstractStore {

    @Override
    protected boolean complies(Food product) {
        return product.getExpiryLevel() > EXPIRED_THRESHOLD;
    }
}
