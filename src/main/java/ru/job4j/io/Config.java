package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines()
                    .filter(str -> str.length() != 0 && !str.startsWith("#"))
                    .peek(str -> {
                        if (str.startsWith("=")
                                || ((str.indexOf('=') == str.lastIndexOf('=') && str.charAt(str.length() - 1) == '=')
                                || str.indexOf('=') == -1)) {
                            String errMsg = "Ошибка в файле:"
                                    + System.lineSeparator()
                                    + "В строке:"
                                    + System.lineSeparator() + str;
                            throw new IllegalArgumentException(errMsg);
                        }
                    })
                    .forEach(str -> {
                int delimiterIdx = str.indexOf('=');
                String key = str.substring(0, delimiterIdx);
                String value = str.substring(delimiterIdx + 1);
                values.put(key, value);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}