package ru.job4j.io.filesearcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

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
            searchByGlob(path, key);
        } else if ("name".equals(pattern)) {
            searchByName(path, key);
        } else {
            searchByRegex(path, key);
        }

        if (files.isEmpty()) {
            System.out.println("No files found");
        } else {
            writeToFile(out);
        }
    }

    private void searchByGlob(Path path, String glob) throws IOException {
        var matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        var searcher = new SearchFiles(matcher::matches);
        Files.walkFileTree(path, searcher);
        files.addAll(searcher.getPaths());
    }

    private void searchByName(Path path, String name) throws IOException {
        var searcher = new SearchFiles(p -> name.equalsIgnoreCase(p.toFile().getName()));
        Files.walkFileTree(path, searcher);
        files.addAll(searcher.getPaths());
    }

    private void searchByRegex(Path path, String regex) throws IOException {
        var searcher = new SearchFiles(p -> p.toFile().toString().matches(regex));
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
