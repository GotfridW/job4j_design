package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result;
        if (Menu.ROOT.equals(parentName)) {
            result = rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            Optional<ItemInfo> parent = findItem(parentName);
            result = parent.map(itemInfo ->
                    itemInfo.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate)))
                    .orElse(false);
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        MenuItemInfo result = null;
        Optional<ItemInfo> itemFound = findItem(itemName);
        if (itemFound.isPresent()) {
            result = new MenuItemInfo(itemFound.get().menuItem, itemFound.get().number);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<ItemInfo> srcIterator = new DFSIterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return srcIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!srcIterator.hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo srcNext = srcIterator.next();
                return new MenuItemInfo(srcNext.menuItem, srcNext.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        ItemInfo result = null;
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo item = iterator.next();
            String itemName = item.menuItem.getName();
            if (itemName.equals(name)) {
                result = item;
                break;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }


    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private static class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
