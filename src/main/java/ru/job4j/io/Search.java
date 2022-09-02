package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        var searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void checkArgument(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("2 arguments required!");
        }
        Path path = Paths.get(args[0]);
        if (!(Files.exists(path) && Files.isDirectory(path))) {
            throw new IllegalArgumentException(
                    String.format("Path not defined: %s", path));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(
                    String.format("Extension not defined: %s", args[1]));
        }
    }

    public static void main(String[] args) throws IOException {
        checkArgument(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }
}
