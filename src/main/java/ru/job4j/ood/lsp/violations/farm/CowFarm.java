package ru.job4j.ood.lsp.violations.farm;

import java.util.HashMap;
import java.util.Map;

/*
В данном классе нарушается принцип LSP за счёт проверки объекта-аргумента animal на соответствие классу Cow.
Подстановка объекта наследника animal другого типа изменит поведение программы.
 */

public class CowFarm implements Farm {
    protected final Map<String, Cow> cattle = new HashMap<>();

    public Map<String, Cow> getCattle() {
        return cattle;
    }

    @Override
    public void add(Animal animal) {
        if (!(animal instanceof Cow)) {
            throw new IllegalArgumentException("animal must be cow");
        }
        cattle.put(animal.getName(), (Cow) animal);
    }
}
