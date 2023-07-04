package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class XmlReportTest {

    @Test
    void whenGenerateReport() {
        Store store = new MemStore();
        Calendar hiredDate = new GregorianCalendar(2020, Calendar.JUNE, 13);
        Calendar firedDate = new GregorianCalendar(2022, Calendar.DECEMBER, 31);
        Employee employeeOne = new Employee("Bill", hiredDate, firedDate, 100);
        Employee employeeTwo = new Employee("Tom", hiredDate, firedDate, 150);
        store.add(employeeOne);
        store.add(employeeTwo);
        Report engine = new XmlReport(store);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee name="Bill" salary="100.0">
                        <hired>13:06:2020 00:00</hired>
                        <fired>31:12:2022 00:00</fired>
                    </employee>
                    <employee name="Tom" salary="150.0">
                        <hired>13:06:2020 00:00</hired>
                        <fired>31:12:2022 00:00</fired>
                    </employee>
                </employees>
                """;
        assertThat(engine.generate(emp -> true)).isEqualTo(expected);
    }
}