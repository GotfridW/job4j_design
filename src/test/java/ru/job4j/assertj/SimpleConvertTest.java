package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "third", "fourth", "fifth");
        assertThat(array).isNotEmpty()
                .hasSize(5)
                .contains("third")
                .contains("first", atIndex(0))
                .containsAnyOf("second", "third", "fourth");
        assertThat(array).anySatisfy(el -> assertThat(el.length()).isGreaterThan(5));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("Alex", "Bill", "Carl", "Johnson");
        assertThat(list).isNotEmpty()
                .hasSize(4)
                .containsAnyOf("Alex", "Johnson")
                .containsExactlyInAnyOrder("Johnson", "Alex", "Bill", "Carl")
                .endsWith("Johnson")
                .doesNotContain("CJ")
                .anyMatch(e -> e.length() > 4);
        assertThat(list).filteredOn(el -> el.length() > 4)
                .hasSize(1)
                .contains("Johnson");
        assertThat(list).first().isEqualTo("Alex");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Alpha", "Beta", null, "Delta", "Beta", "Omega");
        assertThat(set).isNotNull()
                .doesNotHaveDuplicates()
                .hasSize(5)
                .containsNull()
                .containsOnly("Alpha", "Beta", null, "Delta", "Omega");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "third");
        assertThat(map).isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .containsOnlyKeys("first", "second", "third")
                .doesNotContainValue(4)
                .doesNotContainKey(null)
                .containsEntry("second", 1);
    }
}