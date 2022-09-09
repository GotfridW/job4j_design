package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        validate(argsName);
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");

        try (var scanner = new Scanner(new FileInputStream(argsName.get("path")));
             var output = "stdout".equals(out)
                     ? new PrintStream(System.out)
                     : new PrintStream(new FileOutputStream(out))) {
             scanner.useDelimiter(System.lineSeparator());

             var filters = Arrays.stream(filter.split(","))
                     .toList();
             var header = Arrays.stream(scanner.next().split(delimiter))
                     .toList();
             var indices = filters.stream()
                     .mapToInt(header::indexOf)
                     .filter(i -> i != -1)
                     .boxed()
                     .toList();

             String filteredHeader = filterLine(header, indices, delimiter);
             output.println(filteredHeader);
             while (scanner.hasNext()) {
                 var line = Arrays.asList(scanner.next().split(delimiter));
                 var filteredLine = filterLine(line, indices, delimiter);
                 output.println(filteredLine);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    private static String filterLine(List<String> line, List<Integer> indices, String delimiter) {
        return indices.stream()
                .map(line::get)
                .collect(Collectors.joining(delimiter));
    }

    private static void validate(ArgsName argsName) {
        Path path = Paths.get(argsName.get("path"));
        Path out = Paths.get(argsName.get("out"));
        if (!(Files.exists(path) && path.toFile().isFile())) {
            throw new IllegalArgumentException(String.format(
                    "Source file doesn't exist: %s", path));
        }
        if (!(Files.exists(out)) && out.toFile().isFile()) {
            throw new IllegalArgumentException(String.format(
                    "Output file doesn't exist: %s", out));
        }
        String delimiter = argsName.get("delimiter");
        if (!delimiter.equals(";")) {
            throw new IllegalArgumentException(String.format(
                    "Delimiter must be \";\" but was %s", delimiter));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException(String.format(
                    "Args must include 4 arguments but was: %s", args.length));
        }
        var argsName = ArgsName.of(args);
        handle(argsName);
    }
}
