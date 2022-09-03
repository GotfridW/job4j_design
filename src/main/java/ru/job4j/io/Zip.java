package ru.job4j.io;

import java.io.*;
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

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        var params = ArgsName.of(args);
        String[] arr = new String[] {params.get("d"), params.get("e"), params.get("o")};
        Search.checkArgument(new String[] {arr[0], arr[1]});
        Path path = Paths.get(arr[0]);
        Predicate<Path> pred = p -> !p.getFileName().endsWith(arr[1]);
        var paths = Search.search(path, pred);
        zip.packFiles(paths, new File(arr[2]));
    }
}
