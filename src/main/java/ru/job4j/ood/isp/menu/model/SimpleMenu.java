package ru.job4j.ood.isp.menu.model;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        if (parentName == null) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        } else {
            Optional<ItemInfo> parentItemInfo = findItem(parentName);
            if (parentItemInfo.isPresent()) {
                List<MenuItem> children = parentItemInfo.get().menuItem.getChildren();
                MenuItem child = new SimpleMenuItem(childName, actionDelegate);
                if (!children.contains(child)) {
                    children.add(child);
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> menuItemInfo = Optional.empty();
        Optional<ItemInfo> optInfo = findItem(itemName);

        if (optInfo.isPresent()) {
            ItemInfo itemInfo = optInfo.get();
            menuItemInfo = Optional.of(new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
        }
        return menuItemInfo;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new MIIIterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> optInfo = Optional.empty();
        Iterator<ItemInfo> iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (itemInfo.menuItem.getName().equals(name)) {
                optInfo = Optional.of(itemInfo);
            }
        }
        return optInfo;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SimpleMenuItem that = (SimpleMenuItem) o;
            return Objects.equals(name, that.name)
                    && Objects.equals(children, that.children)
                    && Objects.equals(actionDelegate, that.actionDelegate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, actionDelegate);
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

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

    class MIIIterator implements Iterator<MenuItemInfo> {
        Deque<MenuItem> menuItems = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        MIIIterator() {
            Iterator<ItemInfo> itemInfoIterator = new DFSIterator();
            while (itemInfoIterator.hasNext()) {
                ItemInfo itemInfo = itemInfoIterator.next();
                MenuItem menuItem = itemInfo.menuItem;
                menuItems.addLast(menuItem);
                numbers.addLast(itemInfo.number);
            }
        }

        @Override
        public boolean hasNext() {
            return !menuItems.isEmpty();
        }

        @Override
        public MenuItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return new MenuItemInfo(menuItems.removeFirst(), numbers.removeFirst());
        }

    }
}
