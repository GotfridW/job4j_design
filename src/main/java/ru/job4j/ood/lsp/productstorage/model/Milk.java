package ru.job4j.ood.lsp.productstorage.model;

import java.time.LocalDateTime;

public class Milk extends Food {

    public Milk(String name, LocalDateTime createDate, LocalDateTime expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
