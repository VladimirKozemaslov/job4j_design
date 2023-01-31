package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static final int LOAD_TO_CASH = 1;
    public static final int GET_FILE = 2;

    public static final String SELECT = "Выберите действие";
    public static final String DIR_PATH = "Введите путь к кешируемой папке";
    public static final String FILE_NAME = "Введите имя файла";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, чтобы загрузить содержимое файла в кэш.
                Введите 2, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIR_PATH);
        String dirPath = scanner.nextLine();
        DirFileCache fileCache = new DirFileCache(dirPath);
        boolean run = true;

        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == LOAD_TO_CASH) {
                System.out.println(FILE_NAME);
                String file = scanner.nextLine();
                fileCache.get(file);
                System.out.println("Файл успешно добавлен в кэш.");
            } else if (choice == GET_FILE) {
                System.out.println(FILE_NAME);
                String file = scanner.nextLine();
                String content = fileCache.get(file);
                System.out.println(content);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
