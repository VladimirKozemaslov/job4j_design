package ru.job4j.io;

import java.io.*;

public class Analizy {

    private boolean isServerOff = false;

    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))
        ) {
            in.lines().forEach(str -> analyzeStr(str, sb));
            out.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void analyzeStr(String line, StringBuilder sb) {
        int delimiterIdx = line.indexOf(' ');
        String status = line.substring(0, delimiterIdx);
        String date = line.substring(delimiterIdx + 1);
        if ("400".equals(status) || "500".equals(status)) {
            if (!isServerOff) {
                isServerOff = true;
                sb.append(date).append(";");
            }
        } else {
            if (isServerOff) {
                isServerOff = false;
                sb.append(date).append(System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/unavailable.csv", "data/target.csv");
    }
}