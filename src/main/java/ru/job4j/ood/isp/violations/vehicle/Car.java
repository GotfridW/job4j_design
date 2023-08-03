package ru.job4j.ood.isp.violations.vehicle;

public class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Car in motion...");
    }

    @Override
    public void fly() {
    }

    @Override
    public void sail() {
    }

    @Override
    public void dive() {
    }
}
