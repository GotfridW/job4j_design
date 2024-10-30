package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftI = 0;
        int rightI = 0;
        int resultI = 0;
        while (resultI < result.length) {
            if (leftI == left.length) {
                result[resultI++] = right[rightI++];
            } else if (rightI == right.length) {
                result[resultI++] = left[leftI];
            } else if (left[leftI] < right[rightI]) {
                result[resultI++] = left[leftI++];
            } else {
                result[resultI++] = right[rightI++];
            }
        }
        return result;
    }
}
