package ru.job4j.collection.binarytree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeAVLMap<T extends Comparable<T>, V> {
    private Node root;

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key)) {
            root = put(root, key, value);
            result = true;
        }
        return result;
    }

    private Node put(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int diff = key.compareTo(node.key);
            if (diff < 0) {
                node.left = put(node.left, key, value);
            } else if (diff > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    public boolean containsKey(T key) {
        return Objects.nonNull(get(root, key));
    }

    public boolean containsValue(V value) {
        return getMapStream().anyMatch(node -> value.equals(node.value));
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && containsKey(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int diff = key.compareTo(node.key);
        if (diff < 0) {
            node.left = remove(node.left, key);
        } else if (diff > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            result = node.left.balanceFactor >= 0 ? leftRightCase(node) : rightRotation(node);
        } else if (node.balanceFactor > 1) {
            result = node.right.balanceFactor >= 0 ? leftRotation(node) : rightLeftCase(node);
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public V get(T key) {
        Node found = get(root, key);
        return Objects.nonNull(found) ? found.value : null;
    }

    private Node get(Node node, T key) {
        Node result = null;
        if (Objects.nonNull(key) && Objects.nonNull(node)) {
            int diff = key.compareTo(node.key);
            result = (diff == 0) ? node
                    : (diff < 0 && Objects.nonNull(node.left)) ? get(node.left, key)
                    : (diff > 0 && Objects.nonNull(node.right)) ? get(node.right, key)
                    : null;
        }
        return result;
    }

    private Stream<Node> getMapStream() {
        return getMapStream(root, new ArrayList<>());
    }

    private Stream<Node> getMapStream(Node localRoot, List<Node> list) {
        if (Objects.nonNull(localRoot)) {
            getMapStream(localRoot.left, list);
            list.add(localRoot);
            getMapStream(localRoot.right, list);
        }
        return list.stream();
    }

    public Set<T> keySet() {
        return getMapStream(root, new ArrayList<>())
                .map(node -> node.key)
                .collect(Collectors.toSet());
    }

    public Collection<V> values() {
        return getMapStream(root, new ArrayList<>())
                .map(node -> node.value)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private class Node {
        private int balanceFactor;
        private final T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
