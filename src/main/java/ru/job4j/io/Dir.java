package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format(
                    "%s does not exist", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "%s is not a directory", file.getAbsolutePath()));
        }
        System.out.printf("Folder size: %s Gb %n", file.getTotalSpace() / 1073741824);
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("%n%s: %s Mb",
                    subFile.getName(), subFile.length() / 1024);
        }
    }
}
