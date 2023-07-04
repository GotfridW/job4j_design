package ru.job4j.ood.lsp.violations.farm;

public class Cow extends Animal {
    private final boolean isDairy;

    public Cow(String name, boolean isDomestic, boolean isDairy) {
        super(name, isDomestic);
        this.isDairy = isDairy;
    }
}

