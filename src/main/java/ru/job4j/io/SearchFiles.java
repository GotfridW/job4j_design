package ru.job4j.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;
    private List<Path> paths = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.predicate = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (predicate.test(file)) {
                paths.add(file.toAbsolutePath());
            }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return paths;
    }
}
