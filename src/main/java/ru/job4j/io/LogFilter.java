package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            rsl = reader.lines()
                    .filter((str) -> {
                String[] arr = str.split(" ");
                return arr[arr.length - 2].equals("404");
            }).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);

    }
}