package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class ITReportTest {
    private static final String SEPARATOR = System.lineSeparator();

    @Test
    void whenGenerateReport() {
        Store store = new MemStore();
        Calendar hiredDate = new GregorianCalendar(2020, Calendar.JUNE, 13);
        Calendar firedDate = new GregorianCalendar(2022, Calendar.DECEMBER, 31);
        Employee employeeOne = new Employee("Bill", hiredDate, firedDate, 100);
        Employee employeeTwo = new Employee("Tom", hiredDate, firedDate, 150);
        Employee employeeThree = new Employee("Sam", hiredDate, firedDate, 200);
        store.add(employeeOne);
        store.add(employeeTwo);
        store.add(employeeThree);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new ITReport(store, parser);
        StringBuilder builder = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(SEPARATOR)
                .append(employeeOne.getName()).append(',')
                .append(parser.parse(employeeOne.getHired())).append(',')
                .append(parser.parse(employeeOne.getFired())).append(',')
                .append(employeeOne.getSalary()).append(SEPARATOR)
                .append(employeeTwo.getName()).append(',')
                .append(parser.parse(employeeTwo.getHired())).append(',')
                .append(parser.parse(employeeTwo.getFired())).append(',')
                .append(employeeTwo.getSalary()).append(SEPARATOR)
                .append(employeeThree.getName()).append(',')
                .append(parser.parse(employeeThree.getHired())).append(',')
                .append(parser.parse(employeeThree.getFired())).append(',')
                .append(employeeThree.getSalary()).append(SEPARATOR);
        assertThat(engine.generate(emp -> true)).isEqualTo(builder.toString());
    }
}