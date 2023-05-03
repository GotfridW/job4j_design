package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class StringGeneratorTest {
    private final StringGenerator generator = new StringGenerator();
    private final String template = "I am a ${name}, Who are ${subject}? ";

    @Test
    void check() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Mike Wazowski");
        args.put("subject", "you");
        String expected = "I am a Mike Wazowski, Who are you? ";
        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    void whenMissingKeyInMapThenGetException() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Mike Wazowski");
        args.put("question", "Your age?");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenRedundantKeyInMapThenGetException() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Mike Wazowski");
        args.put("subject", "you");
        args.put("question", "Your age?");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenTemplateIsNullThenGetException() {
        String emptyTemplate = null;
        Map<String, String> args = new HashMap<>();
        args.put("name", "Mike Wazowski");
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(emptyTemplate, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenMapIsNullThenGetException() {
        Map<String, String> args = null;
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }
}