package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class AccountingReportTest {
    private static final String SEPARATOR = System.lineSeparator();

    @Test
    void whenGenerateReport() {
        Store store = new MemStore();
        Calendar enter = new GregorianCalendar(2020, Calendar.JUNE, 13);
        Calendar exit = new GregorianCalendar(2022, Calendar.DECEMBER, 31);
        Employee employeeOne = new Employee("Bill", enter, exit, 150);
        Employee employeeTwo = new Employee("Tom", enter, exit, 200);
        store.add(employeeOne);
        store.add(employeeTwo);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new AccountingReport(store, parser, Currency.RUB, Currency.USD);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary (USD);")
                .append(SEPARATOR)
                .append(employeeOne.getName()).append(" ")
                .append(parser.parse(employeeOne.getHired())).append(" ")
                .append(parser.parse(employeeOne.getFired())).append(" ")
                .append(employeeOne.getSalary() * 0.0129)
                .append(SEPARATOR)
                .append(employeeTwo.getName()).append(" ")
                .append(parser.parse(employeeTwo.getHired())).append(" ")
                .append(parser.parse(employeeTwo.getFired())).append(" ")
                .append(employeeTwo.getSalary() * 0.0129)
                .append(SEPARATOR);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}