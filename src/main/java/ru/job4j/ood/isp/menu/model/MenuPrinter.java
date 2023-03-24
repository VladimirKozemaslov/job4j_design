package ru.job4j.ood.isp.menu.model;

import java.util.Iterator;

public class MenuPrinter implements Printer<Menu> {
    public String print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        StringBuilder sb = new StringBuilder();

        while (iterator.hasNext()) {
            Menu.MenuItemInfo itemInfo = iterator.next();
            String line = formMenuLine(determineLevel(itemInfo), itemInfo);
            sb.append(line);
        }
        return sb.toString();
    }

    private String formMenuLine(int level, Menu.MenuItemInfo itemInfo) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < level; i++) {
            sb.append("----");
        }
        if (level > 1) {
            sb.append(" ");
        }
        sb.append(itemInfo.getNumber()).append(" ").append(itemInfo.getName());
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    private int determineLevel(Menu.MenuItemInfo itemInfo) {
        String number = itemInfo.getNumber();
        return number.split("\\.").length;
    }
}
