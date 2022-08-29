package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            String[] arr = sb.toString().split(System.lineSeparator());
            for (String str : arr) {
                int num = Integer.parseInt(str);
                System.out.println(num % 2 == 0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
