package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ITReport implements Report {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public ITReport(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name,Hired,Fired,Salary%s", SEPARATOR));
        for (Employee employee : store.findBy(filter)) {
            builder.append(String.format("%s,%s,%s,%s%s",
                    employee.getName(),
                    parser.parse(employee.getHired()),
                    parser.parse(employee.getFired()),
                    employee.getSalary(),
                    SEPARATOR));
        }
        return builder.toString();
    }
}
