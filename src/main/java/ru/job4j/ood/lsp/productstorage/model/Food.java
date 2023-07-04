package ru.job4j.ood.lsp.productstorage.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class Food {
    private final String name;
    private final LocalDateTime createDate;
    private final LocalDateTime expiryDate;
    private double price;
    private int discount;
}
