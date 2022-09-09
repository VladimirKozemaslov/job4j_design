package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private List<File> files = new LinkedList<>();
    private Set<FileProperty> unique = new HashSet<>();

    private Set<FileProperty> nonUnique = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File f = file.toFile();
        if (!f.isDirectory()) {
            files.add(f);
            FileProperty fileProperty = new FileProperty(f.length(), f.getName());
            if (!unique.add(fileProperty)) {
                nonUnique.add(fileProperty);
            }
        }

        return super.visitFile(file, attrs);
    }

    public void printDuplicates() {
        for (FileProperty originFp : this.nonUnique) {
            System.out.println(String.format("%s - %f Mb", originFp.getName(), (double) originFp.getSize() / (1024 * 1024)));
            for (File file : this.files) {
                FileProperty duplicateFp = new FileProperty(file.length(), file.getName());
                if (originFp.equals(duplicateFp)) {
                    System.out.println(String.format("    %s", file.getAbsolutePath()));
                }
            }
        }
    }
}