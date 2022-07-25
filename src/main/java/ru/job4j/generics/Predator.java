package ru.job4j.generics;

public class Predator extends Animal {
    private String feedingStrategy;

    public Predator() {
    }

    public Predator(Double weight, String habitat, String feedingStrategy) {
        super(weight, habitat);
        this.feedingStrategy = feedingStrategy;
    }
}
