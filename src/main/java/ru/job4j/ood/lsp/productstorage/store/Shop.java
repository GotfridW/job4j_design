package ru.job4j.ood.lsp.productstorage.store;

import ru.job4j.ood.lsp.productstorage.model.Food;

public class Shop extends AbstractStore {

    @Override
    protected boolean complies(Food product) {
        return product.getExpiryLevel() > WAREHOUSE_THRESHOLD && product.getExpiryLevel() <= EXPIRED_THRESHOLD;
    }

    private void applyDiscount(Food product) {
        if (!product.isDiscountApplied() && product.getExpiryLevel() > DISCOUNT_THRESHOLD) {
            double price = product.getPrice();
            product.setPrice(price - ((price / 100) * product.getDiscount()));
            product.setDiscountApplied(true);
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