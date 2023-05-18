package ru.job4j.ood.srp.comparator;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class EmployeeDescBySalary implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}
