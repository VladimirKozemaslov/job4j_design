package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("Параметр %s не был передан", key));
        }
        return value;
    }

    public Map<String, String> getValues() {
        return values;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не передано ни одного параметра");
        }
        for (String arg : args) {
            int prefixIndex = arg.indexOf("-");
            int delimiterIndex = arg.indexOf("=");

            if (prefixIndex == -1) {
                throw new IllegalArgumentException(String.format("У параметра %s отсутствует префикс '-'", arg));
            } else if (delimiterIndex == -1) {
                throw new IllegalArgumentException(String.format("У параметра %s отсутствует разделитель '='", arg));
            }
            String key = arg.substring(prefixIndex + 1, delimiterIndex);
            if (key.length() == 0) {
                throw new IllegalArgumentException(String.format("У параметра %s отсутствует ключ", arg));
            }
            String value = arg.substring(delimiterIndex + 1);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}