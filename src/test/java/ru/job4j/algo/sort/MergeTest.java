package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void mergeSort() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void mergeSortOddLength() {
        int[] array = {12, 15, 23, 4, 6, 10, 35};
        assertThat(Merge.mergesort(array)).containsExactly(4, 6, 10, 12, 15, 23, 35);
    }

    @Test
    void mergeSortSingleElement() {
        int[] array = {42};
        assertThat(Merge.mergesort(array)).containsExactly(42);
    }

    @Test
    void mergeSortWithDuplicates() {
        int[] array = {12, 12, 23, 4, 6, 6, 10, -35, 28};
        assertThat(Merge.mergesort(array)).containsExactly(-35, 4, 6, 6, 10, 12, 12, 23, 28);
    }
}