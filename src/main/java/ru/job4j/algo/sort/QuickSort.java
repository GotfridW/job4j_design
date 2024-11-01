package ru.job4j.algo.sort;

import java.util.Comparator;
import java.util.List;

public class QuickSort {

    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, int start, int end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int mid = breakPartition(sequence, start, end, comparator);
        quickSort(sequence, start, mid - 1, comparator);
        quickSort(sequence, mid + 1, end, comparator);
    }

    private static <T> int breakPartition(List<T> sequence, int start, int end, Comparator<T> comparator) {
        T supportElement = sequence.get(start);
        int left = start + 1;
        int right = end;
        while (true) {
            while (left < end && comparator.compare(sequence.get(left), supportElement) < 0) {
                left++;
            }
            while (comparator.compare(sequence.get(right), supportElement) > 0) {
                right--;
            }
            if (left >= right) {
                break;
            }
            swap(sequence, left++, right--);
        }
        swap(sequence, start, right);
        return right;
    }

    private static <T> void swap(List<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
