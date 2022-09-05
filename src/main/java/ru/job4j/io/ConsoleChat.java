package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> chatLog = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        var sc = new Scanner(System.in);
        System.out.println("Введите сообщение:");
        String msg = sc.nextLine();
        chatLog.add(msg);
        switch (msg) {
            case STOP -> stopLoop();
            case OUT -> {
                saveLog(chatLog);
                System.exit(0);
            }
            default -> reply();
        }
        run();
    }

    private void reply() {
        var phrases = readPhrases();
        var r = new Random();
        String answer = phrases.get(r.nextInt(phrases.size()));
        chatLog.add(answer);
        System.out.println(answer);
    }

    private void stopLoop() {
        System.out.println("Ок, молчу. Для продожения диалога введите \"продолжить\".");
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            input = sc.nextLine();
        } while (!CONTINUE.equals(input));
        reply();
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(
                botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (var pw = new PrintWriter(new FileWriter(
                path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat(args[0], args[1]);
        chat.run();
    }
}
