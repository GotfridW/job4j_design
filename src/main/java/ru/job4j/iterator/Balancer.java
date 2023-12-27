package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        nodes.forEach(node -> {
            if (source.hasNext()) {
                node.add(source.next());
            }
        });
        if (source.hasNext()) {
            split(nodes, source);
        }
    }
}
