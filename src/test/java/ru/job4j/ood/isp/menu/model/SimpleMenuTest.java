package ru.job4j.ood.isp.menu.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {
    @Test
    public void whenSuccessfullyAddMenuItem() {
        Menu menu = new SimpleMenu();
        boolean rsl = menu.add(Menu.ROOT, "Сходить в магазин", () -> System.out.println("Иду в магазин"));
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        assertThat(rsl).isTrue();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).hasFieldOrPropertyWithValue("name", "Сходить в магазин");
    }

    @Test
    public void whenNotAddMenuItem() {
        Menu menu = new SimpleMenu();
        boolean rsl = menu.add("Сходить в магазин", "Купить хлеб", () -> System.out.println("Покупаю хлеб"));
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        assertThat(rsl).isFalse();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void whenSelectRightItem() {
        Menu menu = new SimpleMenu();
        ActionDelegate actionDelegate = () -> System.out.println("Покупаю хлеб");
        menu.add(Menu.ROOT, "Сходить в магазин", () -> System.out.println("Иду в магазин"));
        menu.add("Сходить в магазин", "Купить продукты", () -> System.out.println("Покупаю продукты"));
        menu.add("Купить продукты", "Купить хлеб", () -> System.out.println("Покупаю хлеб"));
        menu.add("Купить продукты", "Купить молоко", () -> System.out.println("Покупаю молоко"));
        menu.add(Menu.ROOT, "Покормить собаку", () -> System.out.println("Кормлю собаку"));
        Optional<Menu.MenuItemInfo> itemInfo = menu.select("Купить хлеб");
        assertThat(itemInfo.isPresent()).isTrue();
        assertThat(itemInfo.get()).isEqualTo(new Menu.MenuItemInfo("Купить хлеб", new ArrayList<>(), actionDelegate, "1.1.1."));
    }

    @Test
    public void whenSelectedItemNotExists() {
        Menu menu = new SimpleMenu();
        ActionDelegate actionDelegate = () -> System.out.println("Покупаю хлеб");
        menu.add(Menu.ROOT, "Сходить в магазин", () -> System.out.println("Иду в магазин"));
        menu.add("Сходить в магазин", "Купить продукты", () -> System.out.println("Покупаю продукты"));
        menu.add("Купить продукты", "Купить хлеб", () -> System.out.println("Покупаю хлеб"));
        menu.add("Купить продукты", "Купить молоко", () -> System.out.println("Покупаю молоко"));
        menu.add(Menu.ROOT, "Покормить собаку", () -> System.out.println("Кормлю собаку"));
        Optional<Menu.MenuItemInfo> itemInfo = menu.select("Купить сыр");
        assertThat(itemInfo.isPresent()).isFalse();
    }

    @Test
    public void whenPrintedMenuEqualsTestMenu() {
        Menu menu = new SimpleMenu();
        ActionDelegate actionDelegate = () -> System.out.println("Покупаю хлеб");
        menu.add(Menu.ROOT, "Сходить в магазин", () -> System.out.println("Иду в магазин"));
        menu.add("Сходить в магазин", "Купить продукты", () -> System.out.println("Покупаю продукты"));
        menu.add("Купить продукты", "Купить хлеб", () -> System.out.println("Покупаю хлеб"));
        menu.add("Купить продукты", "Купить молоко", () -> System.out.println("Покупаю молоко"));
        menu.add(Menu.ROOT, "Покормить собаку", () -> System.out.println("Кормлю собаку"));

        String testMenu = "1. Сходить в магазин"
                + System.lineSeparator()
                + "---- 1.1. Купить продукты"
                + System.lineSeparator()
                + "-------- 1.1.1. Купить хлеб"
                + System.lineSeparator()
                + "-------- 1.1.2. Купить молоко"
                + System.lineSeparator()
                + "2. Покормить собаку"
                + System.lineSeparator();
        Printer<Menu> printer = new MenuPrinter();
        assertThat(testMenu).isEqualTo(printer.print(menu));
    }
}