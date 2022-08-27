package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalizyTest {

    @Test
    void unavailableCheck(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.log").toFile();
        try (var out = new PrintWriter(source)) {
            out.println("200 14:51:28");
            out.println("400 15:11:34");
            out.println("200 15:25:41");
            out.println("500 15:28:17");
            out.println("400 16:03:41");
            out.println("500 16:17:35");
            out.println("200 16:29:01");
        }
        File target = tempDir.resolve("target.csv").toFile();
        var analysis = new Analizy();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        var rsl = new StringBuilder();
        try (var in = new BufferedReader(new FileReader(target))) {
            in.lines().map(line -> line + System.lineSeparator())
                    .forEach(rsl::append);
        }
        assertThat(String.join(System.lineSeparator(),
                "15:11:34;15:25:41", "15:28:17;16:29:01", ""))
                .isEqualTo(rsl.toString());
    }
}