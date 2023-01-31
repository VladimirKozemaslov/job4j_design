package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        if (!key.endsWith(".txt")) {
            throw new IllegalArgumentException("Файл должен быть с расширением .txt");
        }
        Path path = Paths.get(cachingDir, key);
        try (BufferedReader in = new BufferedReader(new FileReader(path.toFile()))) {
            String rsl = in.lines()
                    .reduce("",
                            (result, line) ->
                                    result.concat(line).concat(System.lineSeparator()));
            return rsl;
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Файл с именем %s не найден.", key));
        }
    }
}