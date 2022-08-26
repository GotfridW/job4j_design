package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(
                new FileReader(path))) {
            read.lines()
                    .filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .forEach(s -> {
                        if (!s.contains("=") || s.startsWith("=")
                                || s.split("=", 2)[1].equals("")
                                || s.matches("^=$")) {
                            throw new IllegalArgumentException("Incorrect parameter format");
                        }
                        values.put(s.split("=", 2)[0], s.split("=", 2)[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new NoSuchElementException("Invalid key");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
