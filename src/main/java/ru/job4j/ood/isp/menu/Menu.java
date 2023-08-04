package ru.job4j.ood.isp.menu;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Menu extends Iterable<Menu.MenuItemInfo> {

    String ROOT = "";

    boolean add(String parentName, String childName, ActionDelegate actionDelegate);

    Optional<MenuItemInfo> select(String itemName);

    @AllArgsConstructor
    @EqualsAndHashCode
    class MenuItemInfo {

        private final String name;
        private final List<String> children;
        @EqualsAndHashCode.Exclude
        private final ActionDelegate actionDelegate;
        private final String number;

        public MenuItemInfo(MenuItem menuItem, String number) {
            this.name = menuItem.getName();
            this.children = menuItem.getChildren().stream().map(MenuItem::getName).collect(Collectors.toList());
            this.actionDelegate = menuItem.getActionDelegate();
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public List<String> getChildren() {
            return children;
        }

        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }

        public String getNumber() {
            return number;
        }


    }
}
