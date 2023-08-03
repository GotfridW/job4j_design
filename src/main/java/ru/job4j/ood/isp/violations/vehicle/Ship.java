package ru.job4j.ood.isp.violations.vehicle;


public class Ship implements Vehicle {
    @Override
    public void drive() {
    }

    @Override
    public void fly() {

    }

    @Override
    public void sail() {
        System.out.println("Sailing...");
    }

    @Override
    public void dive() {
    }
}
