package ru.job4j.io.search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class  Search {

    public static void main(String[] args) throws IOException {
        ArgsName arguments = ArgsName.of(args);
        Map<String, String> values = arguments.getValues();
        if (values.size() != 4) {
            throw new IllegalArgumentException("Parameters number must be equal four.");
        }
        Path startDir = Paths.get(values.get("d"));
        String searchType = values.get("t");
        String searchValue = searchType.equals("mask") ? maskToRegex(values.get("n")) : values.get("n");
        Path output = Paths.get(values.get("o"));
        StringBuilder sb = new StringBuilder();
        Predicate<Path> condition;

        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(output.toFile())))) {
            condition = switch (searchType) {
                case "name" -> p -> p.getFileName().toString().equals(searchValue);
                case "regex", "mask" -> p -> Pattern.matches(searchValue, p.getFileName().toString());
                default -> throw new IllegalArgumentException("Недопустимое значение параметра t");
            };
            search(startDir, condition)
                    .forEach(path -> sb.append(path.getFileName()).append(System.lineSeparator()));
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static String maskToRegex(String mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char ch = mask.charAt(i);
            switch (ch) {
                case '*', '?' -> sb.append("\\w").append(ch);
                default -> sb.append(ch);
            }
        }
        return sb.toString();
    }
}