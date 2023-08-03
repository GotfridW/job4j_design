package ru.job4j.ood.isp.violations.vehicle;

public class Plane implements Vehicle {
    @Override
    public void drive() {
    }

    @Override
    public void fly() {
        System.out.println("Flying....");
    }

    @Override
    public void sail() {
    }

    @Override
    public void dive() {
    }
}
