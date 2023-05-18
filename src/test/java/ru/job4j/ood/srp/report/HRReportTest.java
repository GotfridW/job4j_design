package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.comparator.EmployeeDescBySalary;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class HRReportTest {
    private static final String SEPARATOR = System.lineSeparator();

    @Test
    void whenGenerateReport() {
        Store store = new MemStore();
        Calendar enter = new GregorianCalendar(2020, Calendar.JUNE, 13);
        Calendar exit = new GregorianCalendar(2022, Calendar.DECEMBER, 31);
        Employee employeeOne = new Employee("Bill", enter, exit, 150);
        Employee employeeTwo = new Employee("Tom", enter, exit, 200);
        Employee employeeThree = new Employee("Sam", enter, exit, 180);
        store.add(employeeOne);
        store.add(employeeTwo);
        store.add(employeeThree);
        Comparator<Employee> comparator = new EmployeeDescBySalary();
        Report engine = new HRReport(store, comparator);
        StringBuilder builder = new StringBuilder()
                .append("Name; Salary;")
                .append(SEPARATOR)
                .append(employeeTwo.getName()).append(" ")
                .append(employeeTwo.getSalary())
                .append(SEPARATOR)
                .append(employeeThree.getName()).append(" ")
                .append(employeeThree.getSalary())
                .append(SEPARATOR)
                .append(employeeOne.getName()).append(" ")
                .append(employeeOne.getSalary())
                .append(SEPARATOR);
        assertThat(engine.generate(emp -> true)).isEqualTo(builder.toString());
    }
}