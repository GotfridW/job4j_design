package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReport implements Report {
    private static final String SEPARATOR = System.lineSeparator();
    private final Store store;
    private final DateTimeParser<Calendar> parser;
    private final Currency source;
    private final Currency target;
    private final CurrencyConverter converter;

    public AccountingReport(Store store, DateTimeParser<Calendar> parser,
                            Currency source, Currency target) {
        this.converter = new InMemoryCurrencyConverter();
        this.store = store;
        this.parser = parser;
        this.source = source;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder builder = new StringBuilder()
                .append(String.format("Name; Hired; Fired; Salary (%s);%s",
                                      target.toString(), SEPARATOR));
        for (Employee employee : store.findBy(filter)) {
            builder.append(String.format("%s %s %s %s%s",
                    employee.getName(),
                    parser.parse(employee.getHired()),
                    parser.parse(employee.getFired()),
                    converter.convert(source, employee.getSalary(), target),
                    SEPARATOR));
        }
        return builder.toString();
    }
}
