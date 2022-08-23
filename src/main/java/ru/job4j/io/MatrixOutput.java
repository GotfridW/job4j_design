package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class MatrixOutput {
    public static void main(String[] args) {
        int[][] array = new int[9][9];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }
        try (FileOutputStream out = new FileOutputStream("Matrix.txt")) {
            for (int[] i : array) {
                out.write(Arrays.toString(i).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
