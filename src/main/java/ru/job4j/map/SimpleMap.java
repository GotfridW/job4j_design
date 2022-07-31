package ru.job4j.map;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(K key) {
        return key == null ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTab = new MapEntry[capacity];
        for (MapEntry<K, V> e : table) {
            if (e != null) {
                int index = indexFor(hash(e.key));
                newTab[index] = e;
            }
        }
        table = newTab;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(key));
        MapEntry<K, V> entry = table[index];
        if (entry != null && hash(key) == hash(entry.key)
                && Objects.equals(key, entry.key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key));
        MapEntry<K, V> entry = table[index];
        if (entry != null && hash(key) == hash(entry.key)
                && Objects.equals(key, entry.key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor;
            private int number;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = cursor; i < table.length; i++) {
                    if (table[i] != null) {
                    cursor = i;
                    break;
                    }
                }
                return number < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                number++;
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
