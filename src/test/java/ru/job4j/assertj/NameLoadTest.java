package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import javax.naming.Name;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void checkParseWithNoEquals() {
        NameLoad nameLoad = new NameLoad();
        String input = "1one";
        assertThatThrownBy(() -> nameLoad.parse("1one"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(input, "symbol");
    }

    @Test
    void checkParseWithNoKey() {
        NameLoad nameLoad = new NameLoad();
        String first = "=One";
        String second = "=Two";
        assertThatThrownBy(() -> nameLoad.parse(first, second))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(first, second, "key");
    }

    @Test
    void checkParseWithNoValue() {
        NameLoad nameLoad = new NameLoad();
        String key = "Thirteen=";
        assertThatThrownBy(() -> nameLoad.parse(key))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(key)
                .hasMessageContaining("name", "value");
    }
}