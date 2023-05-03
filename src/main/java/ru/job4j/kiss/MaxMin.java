package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    private <T> void validate(List<T> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("The passed list is empty");
        }

    }

    private <T> T getValue(List<T> values, BiPredicate<T, T> predicate) {
        T result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            T value = values.get(i);
            if (predicate.test(result, value)) {
                result = value;
            }
        }
        return result;
    }

    public <T> T max(List<T> values, Comparator<T> comparator) {
        validate(values);
        return getValue(values, (v1, v2) -> comparator.compare(v1, v2) < 0);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        validate(values);
        return getValue(values, (v1, v2) -> comparator.compare(v1, v2) > 0);
    }
}
