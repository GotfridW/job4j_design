package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeTest {

    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.insert("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        AvlTree<String> tree = new AvlTree<>();
        tree.insert("first");
        tree.insert("second");
        tree.insert("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void symmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenFindMinimumThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8}).forEach(tree::insert);
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenFindMaximumThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8}).forEach(tree::insert);
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenPreOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8}).forEach(tree::insert);
        assertThat(tree.inPreOrder()).containsExactly(4, 2, 1, 3, 6, 5, 7, 8);
    }

    @Test
    void whenPostOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8}).forEach(tree::insert);
        assertThat(tree.inPostOrder()).containsExactly(1, 3, 2, 5, 8, 7, 6, 4);
    }
}