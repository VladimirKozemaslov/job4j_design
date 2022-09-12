package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<File>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File f = file.toFile();
        FileProperty fileProperty = new FileProperty(f.length(), f.getName());
        files.putIfAbsent(fileProperty, new ArrayList<>());
        List<File> fList = files.get(fileProperty);
        fList.add(f);
        return super.visitFile(file, attrs);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<File>> pair : files.entrySet()) {
            List<File> files = pair.getValue();
            if (files.size() > 1) {
                FileProperty fp = pair.getKey();
                System.out.println(String.format("%s - %f Mb", fp.getName(), (double) fp.getSize() / (1024 * 1024)));
                for (File file : files) {
                    System.out.println(String.format("    %s", file.getAbsolutePath()));
                }
            }
        }
    }
}