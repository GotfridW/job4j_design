package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        boolean result = false;
        int diff = key.compareTo(node.key);
        if (diff < 0) {
            if (Objects.isNull(node.left)) {
                node.left = new Node(key);
                result = true;
            } else {
                result = put(node.left, key);
            }
        } else if (diff > 0) {
            if (Objects.isNull(node.right)) {
                node.right = new Node(key);
                result = true;
            } else {
                result = put(node.right, key);
            }
        }
        return result;
    }

    public boolean contains(T key) {
        return !Objects.isNull(find(root, key));
    }

    private Node find(Node node, T key) {
        Node result = null;
        if (Objects.nonNull(node)) {
            int diff = key.compareTo(node.key);
            result = (diff == 0) ? node
                    : (diff < 0 && Objects.nonNull(node.left)) ? find(node.left, key)
                    : (diff > 0 && Objects.nonNull(node.right)) ? find(node.right, key)
                    : null;
        }
        return result;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root)) {
            result = remove(root, key);
        }
        return result;
    }

    private boolean remove(Node source, T key) {
        boolean result = true;
        Node current = source;
        Node parent = source;
        boolean isLeft = true;
        while (result && !Objects.equals(current.key, key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                isLeft = true;
                current = current.left;
            } else if (cmp > 0) {
                isLeft = false;
                current = current.right;
            }
            if (Objects.isNull(current)) {
                result = false;
            }
        }
        if (result) {
            if (Objects.isNull(current.left) && Objects.isNull(current.right)) {
                swap(isLeft, source, parent, current, null);
            } else if (Objects.nonNull(current.left) && Objects.isNull(current.right)) {
                swap(isLeft, source, parent, current, current.left);
            } else if (Objects.isNull(current.left)) {
                swap(isLeft, source, parent, current, current.right);
            } else {
                Node heir = getHeir(current);
                swap(isLeft, source, parent, current, heir);
                heir.left = current.left;
            }
            current.key = null;
            current.left = null;
            current.right = null;
        }
        return result;
    }

    private void swap(boolean isLeft, Node source, Node parent, Node current, Node equal) {
        if (Objects.equals(current, source)) {
            root = equal;
        } else if (isLeft) {
            parent.left = equal;
        } else {
            parent.right = equal;
        }
    }

    private Node getHeir(Node delNode) {
        Node nodeParent = delNode;
        Node node = delNode;
        Node current = delNode.right;
        while (current != null) {
            nodeParent = node;
            node = current;
            current = current.left;
        }
        if (node != delNode.right) {
            nodeParent.left = node.right;
            node.right = delNode.right;
        }
        return node;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (Objects.nonNull(localRoot)) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inPreOrder(root, result);
        }
        return result;
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        list.add(localRoot.key);
        if (Objects.nonNull(localRoot.left)) {
            inPreOrder(localRoot.left, list);
        }
        if (Objects.nonNull(localRoot.right)) {
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inPostOrder(root, result);
        }
        return result;
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (Objects.nonNull(localRoot.left)) {
            inPostOrder(localRoot.left, list);
        }
        if (Objects.nonNull(localRoot.right)) {
            inPostOrder(localRoot.right, list);
        }
        list.add(localRoot.key);
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        return Objects.nonNull(node.left) ? minimum(node.left) : node;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        return Objects.nonNull(node.right) ? maximum(node.right) : node;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode, Comparable<T> {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }

        @Override
        public int compareTo(T key) {
            return this.key.compareTo(key);
        }
    }
}
