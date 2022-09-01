package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        validate(args);
        for (String arg : args) {
            var parameter = arg.substring(1).split("=", 2);
            values.put(parameter[0], parameter[1]);
        }
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Couldn't detect any parameters");
        }
        for (var arg : args) {
            if (!arg.matches("^-.+=.+$")) {
                throw new IllegalArgumentException(
                        "Parameter must follow the format \"-key=value\"");
            }
        }
    }

    public static ArgsName of(String[] args) {
        var names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        var jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        var zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
