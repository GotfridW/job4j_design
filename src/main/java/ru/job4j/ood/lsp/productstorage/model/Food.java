package ru.job4j.ood.lsp.productstorage.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public abstract class Food {
    private final String name;
    private final LocalDateTime createDate;
    private final LocalDateTime expiryDate;
    private double price;
    private int discount;
    private float expiryLevel;

    public Food(String name, LocalDateTime createDate, LocalDateTime expiryDate,
                double price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }
}
