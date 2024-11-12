package ru.job4j.algo.newcoll.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Node<E> {
    private E value;
    private List<Node<E>> children = new ArrayList<>();

    public Node(E value) {
        this.value = value;
    }

    @SafeVarargs
    public Node(E value, Node<E>... children) {
        this.value = value;
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public String toString() {
        return String.format("Node{ %s }", value);
    }
}
