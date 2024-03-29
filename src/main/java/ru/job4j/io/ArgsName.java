package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Invalid key: %s", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            validate(arg);
            var parameter = arg.substring(1).split("=", 2);
            values.put(parameter[0], parameter[1]);
        }
    }

    private static void validate(String arg) {
        if (!arg.matches("^-.+=.+$")) {
            throw new IllegalArgumentException(String.format(
                    "Parameter must follow the format \"-key=value\", but was: %s", arg));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Couldn't detect any parameters");
        }
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
