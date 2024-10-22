package ru.job4j.algo.twopointers;

import java.util.*;

/**
 * Класс для решения алгоритмической задачи с двумя указателями.
 * @author Александр Белянов
 */
public class SmallestRangeFinder {

    /**
     * Находит наименьший диапазон в массиве {@code nums} с данным количеством различных элементов.
     * @param nums Исходный массив.
     * @param k Заданное количество уникальных элементов.
     * @return Массив, представляющий искомый наименьший диапазон или null, если такой диапазон не найден.
     */
    public static int[] findSmallestRange(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> rangeValues = new HashMap<>();
        while (right < nums.length) {
            rangeValues.put(nums[right], rangeValues.getOrDefault(nums[right], 0) + 1);
            right++;
            while (rangeValues.size() == k) {
                int windowRange = (right - 1) - left + 1;
                int smallestRange = end - start + 1;
                if (windowRange < smallestRange) {
                    start = left;
                    end = right - 1;
                }
                if (rangeValues.get(nums[left]) == 1) {
                    rangeValues.remove(nums[left]);
                } else {
                    rangeValues.put(nums[left], rangeValues.get(nums[left]) - 1);
                }
                left++;
            }
        }
        return (start == 0 && end == nums.length - 1) ? null : new int[]{start, end};
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
