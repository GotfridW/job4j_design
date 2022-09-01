package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]);
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
