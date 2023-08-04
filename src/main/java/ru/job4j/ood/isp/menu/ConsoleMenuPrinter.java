package ru.job4j.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            String[] numbers = i.getNumber().split("\\.");
            if (numbers.length > 1) {
                buffer.append("----".repeat(numbers.length - 1));
            }
            buffer.append(i.getNumber()).append(i.getName()).append(System.lineSeparator());
        });
        System.out.println(buffer);
    }
}
