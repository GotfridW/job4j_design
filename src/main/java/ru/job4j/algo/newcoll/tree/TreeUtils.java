package ru.job4j.algo.newcoll.tree;

import ru.job4j.collection.SimpleQueue;

import java.util.LinkedList;
import java.util.List;

public class TreeUtils<T> {
    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("The root was null");
        }
        int count = 1;
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        root.getChildren().forEach(queue::push);
        while (!queue.isEmpty()) {
            count += countNode(queue.poll());
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("The root was null");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> values = new LinkedList<>();
        values.add(root.getValue());
        root.getChildren().forEach(queue::push);
        while (!queue.isEmpty()) {
            findAll(queue.poll()).forEach(values::add);
        }
        return values;
    }
}