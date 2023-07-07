package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class JsonReportTest {
    @Test
    void whenGenerateReport() {
        Store store = new MemStore();
        Calendar hiredDate = new GregorianCalendar(2020, Calendar.JUNE, 13);
        Calendar firedDate = new GregorianCalendar(2022, Calendar.DECEMBER, 31);
        Employee employeeOne = new Employee("Bill", hiredDate, firedDate, 100);
        Employee employeeTwo = new Employee("Tom", hiredDate, firedDate, 150);
        store.add(employeeOne);
        store.add(employeeTwo);
        Report engine = new JsonReport(store);
        String expected = """
                [
                  {
                    "name": "Bill",
                    "hired": "13:06:2020 00:00",
                    "fired": "31:12:2022 00:00",
                    "salary": 100.0
                  },
                  {
                    "name": "Tom",
                    "hired": "13:06:2020 00:00",
                    "fired": "31:12:2022 00:00",
                    "salary": 150.0
                  }
                ]""";
        assertThat(engine.generate(emp -> true)).isEqualTo(expected);
    }
}