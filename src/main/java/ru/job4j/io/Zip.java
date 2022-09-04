package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (var zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (var out = new BufferedInputStream(new FileInputStream(
                        path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName args) {
        String[] arr = new String[] {args.get("d"), args.get("e"), args.get("o")};
        Path path = Paths.get(arr[0]);
        if (!(Files.exists(path) && Files.isDirectory(path))) {
            throw new IllegalArgumentException(
                    String.format("Path not defined: %s", path));
        }
        if (!arr[1].startsWith(".")) {
            throw new IllegalArgumentException(
                    String.format("Exclusion not defined: %s", arr[1]));
        }
        if (!arr[2].matches("^.+\\..+$")) {
            throw new IllegalArgumentException(
                    String.format("Target file not defined: %s", arr[2]));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        var params = ArgsName.of(args);
        zip.validate(params);
        Path path = Paths.get(params.get("d"));
        Predicate<Path> pred = p -> !p.getFileName().endsWith(params.get("e"));
        var paths = Search.search(path, pred);
        zip.packFiles(paths, new File(params.get("o")));
    }
}
