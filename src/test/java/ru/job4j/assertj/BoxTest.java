package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotNull()
                .isNotBlank()
                .isMixedCase()
                .startsWithIgnoringCase("s");
    }

    @Test
    void whenThisIsUnknownObject() {
        Box box = new Box(6, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotNull()
                .isNotBlank()
                .isMixedCase()
                .containsWhitespaces();
    }

    @Test
    void whenCubeThen8Vertices() {
        Box box = new Box(8, 5);
        assertThat(box.getNumberOfVertices()).isEqualTo(8)
                .isNotZero()
                .isLessThan(10)
                .isBetween(5, 12)
                .isPositive();
    }

    @Test
    void whenEdgeIsNegative() {
        Box box = new Box(4, -6);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1)
                .isNotPositive()
                .isNotZero()
                .isNegative()
                .isOdd();
    }

    @Test
    void whenBoxIsExist() {
        Box box = new Box(0, 3);
        assertThat(box.isExist()).isTrue()
                .isNotNull();
    }

    @Test
    void whenBoxIsNotExist() {
        Box box = new Box(5, 9);
        assertThat(box.isExist()).isFalse()
                .isNotNull();
    }

    @Test
    void whenSphereAndEdgeIs2ThenAreaIs50dot25() {
        Box box = new Box(0, 2);
        assertThat(box.getArea()).isEqualTo(50.26d,
                withPrecision(0.01d))
                .isCloseTo(50.00d, withinPercentage(1.00d))
                .isNotZero()
                .isGreaterThan(40.00d);
    }

    @Test
    void whenTetrahedronAndEdgeIs4ThenAreaIs27dot71() {
        Box box = new Box(4, 4);
        assertThat(box.getArea()).isEqualTo(27.71d, withPrecision(0.01d))
                .isNotZero()
                .isGreaterThan(25.00d)
                .isFinite()
                .isStrictlyBetween(27d, 28d);
    }
}