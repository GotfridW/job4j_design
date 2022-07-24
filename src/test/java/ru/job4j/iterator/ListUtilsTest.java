package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenElementIsMoreThan10ThenRemove() {
        input.add(10);
        input.add(11);
        input.add(12);
        ListUtils.removeIf(input, el -> el > 10);
        assertThat(input).hasSize(3).containsSequence(1, 3, 10);
    }

    @Test
    void whenElementEquals1ThenReplace() {
        input.add(1);
        input.add(1);
        ListUtils.replaceIf(input, el -> el == 1, 2);
        assertThat(input).hasSize(4).containsSequence(2, 3, 2, 2);
    }

    @Test
    void whenRemovingCollectionFromInput() {
        input.add(6);
        input.add(9);
        input.add(12);
        ListUtils.removeAll(input, List.of(3, 9, 12));
        assertThat(input).hasSize(2).containsSequence(1, 6);
    }
}