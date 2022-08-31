package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        files.putIfAbsent(fileProperty, new ArrayList<>());
        files.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void getDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : files.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey());
                entry.getValue().forEach(System.out::println);
            }
        }
    }
}
