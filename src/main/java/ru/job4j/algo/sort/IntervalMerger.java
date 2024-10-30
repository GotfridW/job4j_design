package ru.job4j.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>(intervals.length);
        int[] previous = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if (previous[1] >= current[0]) {
                previous[1] = Math.max(previous[1], current[1]);
            } else {
                merged.add(previous);
                previous = current;
            }
        }
        merged.add(previous);
        return merged.toArray(new int[merged.size()][]);
    }
}
