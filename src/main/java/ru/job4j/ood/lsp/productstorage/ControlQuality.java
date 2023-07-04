package ru.job4j.ood.lsp.productstorage;

import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.store.Store;

import java.util.List;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void shipOut(Food product) {
        for (Store store : storeList) {
            if (store.add(product)) {
                break;
            }
        }
    }
}
