package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            boolean isServerOff = false;
            List<String> recList = in.lines().toList();
            for (String line : recList) {
                int delimiterIdx = line.indexOf(' ');
                String status = line.substring(0, delimiterIdx);
                String date = line.substring(delimiterIdx + 1);
                if ((status.equals("400") || status.equals("500"))) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                )
        )) {
            out.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/unavailable.csv", "data/target.csv");
    }
}