package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multiplication_table.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    String str = i + " * " + j + " = " + i * j;
                    out.write(str.getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
