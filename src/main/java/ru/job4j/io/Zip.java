package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
//        for (File source : sources) {
//            packSingleFile(source, target);
//        }
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

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Path source = Paths.get(argsName.get("d"));
        File target = new File(argsName.get("o"));
        String exclude = argsName.get("e");
        List<File> files;

        if (source.toFile().exists()) {
            try {
                files = Search.search(source, p -> !p.toFile().getName().endsWith(exclude)).stream()
                        .map(Path::toFile)
                        .toList();
                Zip zip = new Zip();
                zip.packFiles(files, target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        Zip zip = new Zip();
//        zip.packSingleFile(
//                new File("./pom.xml"),
//                new File("./pom.zip")
//        );
    }
}