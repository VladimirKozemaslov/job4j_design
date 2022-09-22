package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> botAnswers = readPhrases();
            List<String> log = new LinkedList<>();
            boolean isBotAnswer = true;
            String userLastMsg = reader.readLine();
            while (!OUT.equals(userLastMsg)) {
                log.add(userLastMsg);
                if (STOP.equals(userLastMsg)) {
                    isBotAnswer = false;
                } else if (CONTINUE.equals(userLastMsg)) {
                    isBotAnswer = true;
                }
                if (isBotAnswer) {
                    int index = (int) (Math.random() * 10) % botAnswers.size();
                    String answer = botAnswers.get(index);
                    System.out.println(answer);
                    log.add(answer);
                }
                userLastMsg = reader.readLine();
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = null;
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            phrases = br.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            for (String phrase : log) {
                pw.println(phrase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat_log.txt", "./data/bot_answers.txt");
        cc.run();
    }
}