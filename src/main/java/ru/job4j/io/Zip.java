package ru.job4j.io;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateParams(Map<String, String> values) {
        List<String> validKeys = List.of("d", "o", "e");
        Set<String> keys = values.keySet();
        if (!validKeys.containsAll(keys)) {
            throw new IllegalArgumentException("One of parameters key is not valid");
        }
        for (String key : validKeys) {
            if (values.get(key) == null) {
                throw new IllegalArgumentException(String.format("Value of '%s' parameter is null", key));
            }
        }
        Path directory = Paths.get(values.get("d"));
        if (!directory.toFile().exists() || !directory.toFile().isDirectory()) {
            throw new IllegalArgumentException("Source path is not exists or not a directory");
        }
        String exclude = values.get("e");
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("File extension must begin with '.'");
        }

        String output = values.get("o");
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Name of archive must end with '.zip'");
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Parameters number must be equal three.");
        }
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validateParams(argsName.getValues());
        Path source = Paths.get(argsName.get("d"));
        File target = new File(argsName.get("o"));
        String exclude = argsName.get("e");
        try {
            List<File> files = Search.search(source, p -> !p.toFile().getName().endsWith(exclude)).stream()
                    .map(Path::toFile)
                    .toList();
            zip.packFiles(files, target);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}