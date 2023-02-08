package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    public static final int LOAD = 1;
    public static final int GET = 2;

    public static final String DIR =
            "Please enter directory path";
    public static final String SELECT = "Choose one option";
    public static final String END = "End of program";
    public static final String FILENAME =
            "Please enter the file name";

    public static final String MENU = """
                1. Upload file contents to cache
                2. Get file contents from cache
                Any other number - Exit
            """;

    private static void start(Scanner scanner, DirFileCache cache) {
        boolean run = true;
        do {
            System.out.printf("%s%n%s", "=".repeat(45), MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (LOAD == userChoice) {
                System.out.println(FILENAME);
                cache.get(scanner.nextLine());
                System.out.println("Successfully loaded to cache");
            } else if (GET == userChoice) {
                System.out.println(FILENAME);
                System.out.println(cache.get(scanner.nextLine()));
            } else {
                run = false;
                System.out.println(END);
            }
        } while (run);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIR);
        String dir = scanner.nextLine();
        Path dirPath = Path.of(dir);
        if (!Files.isDirectory(dirPath)) {
            throw new IllegalArgumentException(String.format("Doesn't exist: %s", dirPath));
        }
        start(scanner, new DirFileCache(dir));
    }
}