package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MaxMinTest {
    private final List<Integer> list = List.of(2, 5, 1, 7, 3, 9, 4);
    private final MaxMin maxMin = new MaxMin();

    @Test
    void whenFindingMaximumValueThenResultIsNine() {
        Comparator<Integer> comparator = Integer::compareTo;
        assertThat(maxMin.max(list, comparator)).isEqualTo(9);
    }

    @Test
    void whenFindingMinimumValueThenResultIsOne() {
        Comparator<Integer> comparator = Integer::compareTo;
        assertThat(maxMin.min(list, comparator)).isEqualTo(1);
    }

    @Test
    void whenListIsEmptyThenThrowException() {
        List<String> strings = new ArrayList<>();
        Comparator<String> comparator = String::compareTo;
        assertThatThrownBy(() -> maxMin.max(strings, comparator))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
        assertThatThrownBy(() -> maxMin.max(strings, comparator))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

}