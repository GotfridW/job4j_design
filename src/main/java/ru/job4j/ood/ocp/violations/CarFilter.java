package ru.job4j.ood.ocp.violations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Stream;

/*
При необходимости внедрения новых фильтров потребуется изменить данный класс. Правильным решением здесь будет
использование единственного фильтрующего метода, принимающего в качестве аргумента предикат.
 */

@AllArgsConstructor
@Getter
public class CarFilter {
    private final List<Car> cars;

    public Stream<Car> filterByColor(String color) {
        return cars.stream().filter(car -> color.equals(car.getColor()));
    }

    public Stream<Car> filterByType(String type) {
        return cars.stream().filter(car -> type.equals(car.getType()));
    }

    public Stream<Car> filterByNameAndType(String name, String type) {
        return cars.stream().filter(car -> name.equals(car.getName()) && type.equals(car.getType()));
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Car {
        private final String name;
        private final String type;
        private final String color;
    }

    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car("Toyota", "Minivan", "black"),
                new Car("Nissan", "Sedan", "silver"),
                new Car("Mazda", "Sedan", "black")
        );
        CarFilter carFilter = new CarFilter(cars);
        carFilter.filterByType("Sedan").forEach(System.out::println);
    }
}
