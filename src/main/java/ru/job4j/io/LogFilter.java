package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines()
                    .filter(s -> s.contains(" 404 "))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
