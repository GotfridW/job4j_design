package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("Even.txt")) {
            int i;
            StringBuilder builder = new StringBuilder();
            while ((i = in.read()) != -1) {
                builder.append((char) i);
            }
            String[] str = builder.toString().split(System.lineSeparator());
            Arrays.stream(str)
                    .mapToInt(Integer::parseInt)
                    .forEach(e -> System.out.println(e + " even: " + (e % 2 == 0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}