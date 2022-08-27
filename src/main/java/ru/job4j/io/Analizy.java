package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (var read = new BufferedReader(new FileReader(source));
            var writer = new PrintWriter(new FileOutputStream(target))) {
            var online = true;
            while (read.ready()) {
                var line = read.readLine();
                if (online && serverDown(line)) {
                    online = false;
                    writer.printf("%s;", line.split(" ")[1]);
                } else if (!online && !serverDown(line)) {
                    online = true;
                    writer.printf("%s%n", line.split(" ")[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean serverDown(String input) {
        return input.startsWith("4") || input.startsWith("5");
    }

    public static void main(String[] args) {
        Analizy analysis = new Analizy();
        analysis.unavailable("server.log", "unavailable.csv");
    }
}
