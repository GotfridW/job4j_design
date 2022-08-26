package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comments.properties";
        Config cfg = new Config(path);
        cfg.load();
        assertThat(cfg.value("name")).isEqualTo("Sherlock");
    }

    @Test
    void whenPairsWithCommentsAndEmptyLines() {
        String path = "./data/pairs_with_comments_and_empty_lines.properties";
        Config cfg = new Config(path);
        cfg.load();
        assertThat(cfg.value("country")).isNotNull()
                .isEqualTo("USA");
        assertThat(cfg.value("city")).isEqualTo("Portland");
    }

    @Test
    void whenLineHasMultipleEqualSigns() {
        String path = "./data/pairs_with_multiple_equal_signs.properties";
        Config cfg = new Config(path);
        cfg.load();
        assertThat(cfg.value("time")).isNotNull()
                .isEqualTo("money=happiness");
        assertThat(cfg.value("silence")).isEqualTo("gold=");
    }

    @Test
    void whenLineWithoutKeyThenThrowException() {
        String path = "./data/line_without_key.properties";
        String s = "=black";
        Config cfg = new Config(path);
        assertThatThrownBy(cfg::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+$")
                .hasMessageContaining("Incorrect", s);
    }

    @Test
    void whenLineWithoutValueThenThrowException() {
        String path = "./data/line_without_value.properties";
        Config cfg = new Config(path);
        assertThatThrownBy(cfg::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+$")
                .hasMessageContaining("Incorrect");
        assertThat(cfg.value("shape")).isNotNull()
                .isEqualTo("circle");
    }

    @Test
    void whenLineWithoutBothKeyAndValueThenThrowException() {
        String path = "./data/line_without_both_key_and_value.properties";
        Config cfg = new Config(path);
        assertThatThrownBy(cfg::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenLineHasNoEqualSignThenThrowException() {
        String path = "./data/pair_without_equal_sign.properties";
        Config cfg = new Config(path);
        assertThatThrownBy(cfg::load).isInstanceOf(IllegalArgumentException.class);
    }
}