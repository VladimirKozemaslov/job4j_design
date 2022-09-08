package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnalizyTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("unavailable.csv").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 08:30:19");
            out.println("200 08:57:13");
            out.println("300 10:58:01");
            out.println("400 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
            out.println("400 11:26:22");
            out.println("500 11:28:14");
            out.println("200 12:05:18");
        }
        File target  = tempDir.resolve("target.csv").toFile();
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(line -> rsl.append(line).append(" "));
        }
        assertThat("10:59:01;11:01:02 11:26:22;12:05:18 ").isEqualTo(rsl.toString());
    }
}