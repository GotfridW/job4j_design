package ru.job4j.ood.ocp.violations;

import java.util.ArrayList;
import java.util.List;

/*
В данном классе, метод getDiscount(), возвращающий размер скидки в зависимости от количества товаров в корзине,
нарушает метод OCP: определение конкретных условий здесь порождает потенциальную необходимость изменения логики
(условий). Правильным решением будет реализовать отдельные классы дисконт-правил и дисконт-калькулятора,
реализующих соответствующие интерфейсы.
 */

public class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public int getDiscount() {
        int discount = 0;
        if (items.size() > 5 && items.size() <= 10) {
            discount = 10;
        }
        if (items.size() > 10 && items.size() <= 20) {
            discount = 15;
        }
        if (items.size() > 20 && items.size() <= 35) {
            discount = 25;
        }
        return discount;
    }

    public void add(Item item) {
    }

    public void remove(Item item) {
    }

    public static class Item {
    }
}
