package ru.job4j.algo.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        boolean result = false;
        Node<T> parentNode = findByKey(root, parent).orElse(null);
        if (parentNode != null && findByKey(parentNode, child).isEmpty()) {
            parentNode.getChildren().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("The root was null");
        }
        Optional<Node<T>> result = Optional.empty();
        if (key.equals(root.getValue())) {
            result = Optional.of(root);
        } else {
            SimpleStack<Node<T>> stack = new SimpleStack<>();
            root.getChildren().forEach(stack::push);
            while (!stack.isEmpty()) {
                result = findByKey(stack.pop(), key);
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("The root was null");
        }
        Optional<Node<T>> result = Optional.empty();
        if (key.equals(root.getValue())) {
            result = Optional.of(root);
        } else {
            List<Node<T>> children = root.getChildren();
            for (Node<T> child : children) {
                if (findByKey(child, key).isPresent()) {
                    result = Optional.of(child);
                    children.remove(child);
                    break;
                }
            }
        }
        return result;
    }

}