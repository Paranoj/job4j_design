package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of parameters to realize the program.");
        }
        if (!args[1].startsWith(".") || !(args[1].length() > 1)) {
            throw new IllegalArgumentException("Wrong format added to args.");
        }
        if (!Files.exists(Path.of(args[0]))) {
            throw new IllegalArgumentException("File doesn't exist in the chosen directory.");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Directory doesn't exist.");
        }
    }
}
