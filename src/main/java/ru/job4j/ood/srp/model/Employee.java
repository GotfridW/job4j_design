package ru.job4j.ood.srp.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
    @EqualsAndHashCode.Include private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", hired=" + hired
                + ", fired=" + fired
                + ", salary=" + salary
                + '}';
    }
}


