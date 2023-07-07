package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarJsonAdapter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private final Store store;
    private final GsonBuilder builder;

    public JsonReport(Store store) {
        this.store = store;
        this.builder = new GsonBuilder();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return builder.setPrettyPrinting()
                .registerTypeAdapter(Calendar.class, new CalendarJsonAdapter())
                .registerTypeAdapter(GregorianCalendar.class, new CalendarJsonAdapter())
                .create()
                .toJson(store.findBy(filter));
    }
}
