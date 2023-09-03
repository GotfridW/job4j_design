package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    public Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

    }

    @Test
    public void whenAddThenReturnSame() {
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
        menu.forEach(i -> i.getActionDelegate().delegate());
    }

    @Test
    public void whenAddToNonExistentItemThenFalse() {
        assertThat(menu.add("Сделать уроки", "Выучить стих", STUB_ACTION)).isFalse();
    }
    @Test
    public void whenSelectAction() {
        Menu.MenuItemInfo itemInfo = menu.select("Купить молоко").get();
        assertThat(itemInfo.getNumber()).isEqualTo("1.1.2.");
        assertThat(itemInfo.getChildren()).isEmpty();
    }

    @Test
    public void whenPrintMenu() {
        MenuPrinter printer = new ConsoleMenuPrinter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        printer.print(menu);
        String ln = System.lineSeparator();
        String expected = String.join(ln,
                "1.Сходить в магазин",
                "----1.1.Купить продукты",
                "--------1.1.1.Купить хлеб",
                "--------1.1.2.Купить молоко",
                "2.Покормить собаку", ln);
        assertThat(out.toString()).isEqualTo(expected);
    }
}