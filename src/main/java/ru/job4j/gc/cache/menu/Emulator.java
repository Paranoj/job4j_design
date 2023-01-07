package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {

    public static final Integer LOAD_IN_CASH = 1;
    public static final Integer GET_FROM_CASH = 2;

    public static final String MENU = """
                Введите 1, чтобы загрузить содержимое файла в кэш.
                Введите 2, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static final String SELECT = "Выберите меню.";
    public static final String PATH = "Укажите кэшируемую директорию.";
    public static final String EXIT = "Конец работы.";
    public static final String FILE_LOAD = "Введите имя файла для загрузки в кэш.";
    public static final String FILE_GETTER = "Введите имя файла для выгрузки из кэша.";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(PATH);
        String dir = scanner.nextLine();
        if (!Files.exists(Paths.get(dir))) {
            throw new IllegalArgumentException("Directory doesn't exist");
        }
        DirFileCache dirFileCache = new DirFileCache(dir);
        start(scanner, dirFileCache);
    }

    public static void start(Scanner scanner, DirFileCache dirFileCache) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (LOAD_IN_CASH == userChoice) {
                System.out.println(FILE_LOAD);
                String key = scanner.nextLine();
                dirFileCache.put(key, dirFileCache.get(key));
            } else if (GET_FROM_CASH == userChoice) {
                System.out.println(FILE_GETTER);
                System.out.println(dirFileCache.get(scanner.nextLine()));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
