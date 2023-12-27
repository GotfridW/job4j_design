package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private final Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    private int nextNonNull(int index) {
        while (index < data.length - 1 && data[index] == null) {
            index++;
        }
        return index;
    }

    @Override
    public boolean hasNext() {
        index = nextNonNull(index);
        return index < data.length && data[index] != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
