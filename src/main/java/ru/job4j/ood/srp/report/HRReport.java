package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRReport implements Report {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store store;
    private final Comparator<Employee> comparator;

    public HRReport(Store store, Comparator<Employee> comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name; Salary;%s", SEPARATOR));
        List<Employee> employees = store.findBy(filter);
        employees.sort(comparator);
        for (Employee employee : employees) {
            builder.append(String.format("%s %s%s",
                    employee.getName(),
                    employee.getSalary(),
                    SEPARATOR));
        }
        return builder.toString();
    }
}
