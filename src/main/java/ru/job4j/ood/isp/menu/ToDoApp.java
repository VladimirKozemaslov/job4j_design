package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.model.*;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ToDoApp {
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    private final Menu menu;
    private Scanner scanner;

    public ToDoApp() {
        this.menu = new SimpleMenu();
        this.scanner = new Scanner(System.in);
    }

    public ToDoApp(Menu menu) {
        this.menu = menu;
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        boolean run = true;
        Printer<Menu> printer = new MenuPrinter();
        Pattern choosePattern = Pattern.compile(".+");
        Pattern addPattern = Pattern.compile(".+\\+");

        while (run) {
            System.out.println(getDescriptionPhrase() + System.lineSeparator());
            String input = scanner.nextLine();
            if ("+".equals(input)) {
                addItemToRoot();
            } else if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Завершение работы");
                run = false;
            }  else if ("menu".equalsIgnoreCase(input)) {
                showMenu(printer);
            } else if (addPattern.matcher(input).matches()) {
                addItemToParent(input.substring(0, input.length() - 1));
            } else if (choosePattern.matcher(input).matches()) {
                executeAction(input);
            } else {
                System.out.println("Неверный ввод, попробуйте еще раз.");
            }
        }
    }

    private void showMenu(Printer<Menu> printer) {
        StringBuilder sb = new StringBuilder("Меню:")
                .append(System.lineSeparator());
        sb.append(printer.print(menu));
        System.out.println(sb);
    }

    private String getDescriptionPhrase() {
        return  "Введите menu для показа меню."
                + System.lineSeparator()
                + "Введите + для добавления элемента в корень меню."
                + System.lineSeparator()
                + "Введите [название_пункта_меню]+ для добавления подпункта к пункту меню."
                + System.lineSeparator()
                + "Введите название пункта меню для выполнения действия для данного пункта."
                + System.lineSeparator()
                + "Введите exit для выхода.";
    }

    private void addItemToRoot() {
        System.out.println("Введите название пункта меню: ");
        String input = scanner.nextLine();
        boolean rsl = menu.add(Menu.ROOT, input, DEFAULT_ACTION);
        if (rsl) {
            System.out.println("Пункт добавлен в корень меню.");
        }
    }

    private void addItemToParent(String parent) {
        if (menu.select(parent).isEmpty()) {
            System.out.println("Данный пункт отсутствует в меню.");
        } else {
            System.out.println("Введите название для нового подпункта меню: ");
            String child = scanner.nextLine();
            boolean rsl = menu.add(parent, child, DEFAULT_ACTION);
            if (rsl) {
                System.out.println("Подпункт добавлен.");
            }
        }
    }

    private void executeAction(String number) {
        Optional<Menu.MenuItemInfo> itemInfo = menu.select(number);
        itemInfo.ifPresentOrElse(
                menuItemInfo -> menuItemInfo.getActionDelegate().delegate(),
                () -> System.out.println("Данный пункт отсутствует в меню."));
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        menu.add(null, "Сходить в магазин", () -> System.out.println("Иду в магазин"));
        menu.add("Сходить в магазин", "Купить продукты", () -> System.out.println("Покупаю продукты"));
        menu.add("Купить продукты", "Купить хлеб", () -> System.out.println("Покупаю хлеб"));
        menu.add("Купить продукты", "Купить молоко", () -> System.out.println("Покупаю молоко"));
        menu.add(null, "Покормить собаку", () -> System.out.println("Кормлю собаку"));

        ToDoApp toDoApp = new ToDoApp(menu);
        toDoApp.init();
    }
}
