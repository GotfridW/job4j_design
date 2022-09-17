package ru.job4j.io.filesearcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {
    private final List<Path> files = new ArrayList<>();

    public void run(String[] args) throws IOException {
        var params = ArgsName.of(args);
        validate(params);
        Path path = Paths.get(params.get("d"));
        String key = params.get("n");
        String pattern = params.get("t");
        Path out = Paths.get(params.get("o"));
        if ("mask".equals(pattern)) {
            var matcher = FileSystems.getDefault().getPathMatcher("glob:" + key);
            search(path, matcher::matches);
        } else if ("name".equals(pattern)) {
            search(path, p -> key.equalsIgnoreCase(p.toFile().getName()));
        } else {
            search(path, p -> p.toFile().toString().matches(key));
        }
        if (files.isEmpty()) {
            System.out.println("No files found");
        } else {
            writeToFile(out);
        }
    }

    private void search(Path path, Predicate<Path> predicate) throws IOException {
        var searcher = new SearchFiles(predicate);
        Files.walkFileTree(path, searcher);
        files.addAll(searcher.getPaths());
    }

    private void writeToFile(Path output) {
        try (var out = new PrintWriter(new FileWriter(output.toFile()))) {
            out.printf("Search results:%n%n");
            files.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName args) {
        Path path = Paths.get(args.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format(
                    "Doesn't exist: %s", path));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format(
                    "Not a directory: %s", path));
        }
        String pattern = args.get("t");
        if (!("name".equals(pattern) || "mask".equals(pattern)
                || "regex".equals(pattern))) {
            throw new IllegalArgumentException(String.format(
                    "Invalid search type parameter: %s", pattern));
        }
    }
}
