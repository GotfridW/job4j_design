package ru.job4j.ood.isp.menu;

import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Scanner;

@AllArgsConstructor
public class TodoApp {

    private static final int ADD_ROOT_ITEM = 1;
    private static final int ADD_SUB_ITEM = 2;
    private static final int CALL_ITEM = 3;
    private static final int PRINT_MENU = 4;

    private static final String ROOT_ITEM_NAME = "Enter root item name:";
    private static final String SUB_ITEM_NAME = "Enter sub-item name:";
    private static final String END = "End of work";
    private static final String OPTIONS_MENU = """
    Enter one of the following options:
    1 - Add item to menu root
    2 - Add sub-item to menu item
    3 - Select item
    4 - Show menu items""";

    private static final ActionDelegate DEFAULT_ACTION = new OutputAction("Action on selection");

    private static void init(Menu menu, Scanner scanner) {
        MenuPrinter printer = new ConsoleMenuPrinter();
        boolean run = true;
        do {
            System.out.println(OPTIONS_MENU);
            int choice = Integer.parseInt(scanner.nextLine());
            if (ADD_ROOT_ITEM == choice) {
                System.out.println(ROOT_ITEM_NAME);
                String newItem = scanner.nextLine();
                menu.add(Menu.ROOT, newItem, DEFAULT_ACTION);
            } else if (ADD_SUB_ITEM == choice) {
                System.out.println(ROOT_ITEM_NAME);
                String rootItemName = scanner.nextLine();
                System.out.println(SUB_ITEM_NAME);
                String newItem = scanner.nextLine();
                menu.add(rootItemName, newItem, DEFAULT_ACTION);
            } else if (CALL_ITEM == choice) {
                String itemName = scanner.nextLine();
                try {
                    menu.select(itemName).orElseThrow().getActionDelegate().delegate();
                } catch (NoSuchElementException e) {
                    System.out.printf("item with name \"%s\" not found", itemName);
                }
            } else if (PRINT_MENU == choice) {
                printer.print(menu);
            } else {
                run = false;
            }
        } while (run);
        System.out.println(END);
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        init(menu, scanner);
    }
}
