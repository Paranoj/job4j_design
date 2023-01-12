package ru.job4j.softaria;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Start {

    /**
     * Созданы хранилища для объектов Website с целью демонстрации работы приложения.
     * Вывод письма в .txt файл.
     */
    public static void main(String[] args) {
        Changes changes = new ChangesWebsitesCode();
        Map<String, String> mapY = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1","<12345>",
                "https://career.habr.com/vacancies/java_developer?=page2", "<12345>",
                "https://career.habr.com/vacancies/java_developer?=page3", "<12345>",
                "https://career.habr.com/vacancies/java_developer?=page5", "<123>"));
        Map<String, String> mapT = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1","<1234>",
                "https://career.habr.com/vacancies/java_developer?=page4","<12345>",
                "https://career.habr.com/vacancies/java_developer?=page5","<12345>"));
        try (FileOutputStream out = new FileOutputStream("rsl.txt")) {
            out.write(changes.letter(mapY, mapT).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
