package ru.job4j.generics;

public class Tiger extends Predator {
    private String breed;
    private String region;

    public Tiger() {
    }

    public Tiger(Double weight, String habitat, String feedingStrategy, String breed, String region) {
        super(weight, habitat, feedingStrategy);
        this.breed = breed;
        this.region = region;
    }
}
