package ru.job4j.ood.lsp.productstorage.calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.model.Milk;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FoodExpirationCalculatorTest {
    private static ExpirationCalculator calculator;
    private static final LocalDateTime NOW = LocalDateTime.now();

    @BeforeAll
    static void setUp() {
        calculator = new FoodExpirationCalculator();
    }

    @Test
    void whenProductWithInvalidCreateDateThenException() {
        Food product = new Milk(
                "Milk", NOW.plusDays(2), NOW.plusDays(10), 50, 20);
        assertThatThrownBy(() -> calculator.calculate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenProductWithInvalidExpiryDateThenException() {
        Food product = new Milk(
                "Milk", NOW.minusDays(5), NOW.minusDays(10), 50, 20);
        assertThatThrownBy(() -> calculator.calculate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenProductIsHalfFreshThen50() {
        Food product = new Milk(
                "Milk", NOW.minusDays(5), NOW.plusDays(5), 50, 20);
        float expected = 50.0f;
        assertThat(calculator.calculate(product)).isEqualTo(expected);
    }
}