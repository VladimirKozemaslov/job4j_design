package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Parameters number must be equal four.");
        }
        if (!args[0].endsWith(".csv")) {
            throw new IllegalArgumentException("File extension should end with '.csv'");
        }
        try {
            handle(ArgsName.of(args));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void handle(ArgsName argsName) throws IllegalArgumentException {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String outPath = argsName.get("out");
        String filterStr = argsName.get("filter");

        int[] indexes = getColumnIndexes(path, delimiter, filterStr);
        if (indexes != null) {
            StringBuilder data = new StringBuilder();
            try (Scanner fileScanner = new Scanner(new FileReader(path)).useDelimiter(System.lineSeparator())) {
                while (fileScanner.hasNext()) {
                    String line = fileScanner.next();
                    String[] columns = line.split(delimiter);
                    List<String> filteredColumns = new ArrayList<>();
                    for (int index : indexes) {
                        filteredColumns.add(columns[index]);
                    }
                    data.append(String.join(delimiter, filteredColumns)).append(System.lineSeparator());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            if ("stdout".equals(outPath)) {
                System.out.println(data);
            } else {
                try (BufferedWriter out = new BufferedWriter(new FileWriter(outPath))) {
                    out.write(data.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int[] getColumnIndexes(String path, String delimiter, String filterStr) {
        String[] filters = filterStr.split(",");
        int[] indexes = new int[filters.length];
        try (Scanner fileScanner = new Scanner(new FileReader(path)).useDelimiter(System.lineSeparator())) {
            if (fileScanner.hasNext()) {
                String columnStr = fileScanner.next();
                String[] columns = columnStr.split(delimiter);
                for (int i = 0; i < filters.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        if (columns[j].equals(filters[i])) {
                            indexes[i] = j;
                            break;
                        }
                    }
                }
            } else {
                indexes = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexes;
    }
}