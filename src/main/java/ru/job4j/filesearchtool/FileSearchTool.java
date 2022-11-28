package ru.job4j.filesearchtool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileSearchTool {

    private static void validate(ArgsName argsName) {
        File file = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "The directory for searching doesn't exist: %s", file.getAbsoluteFile()));
        }
        if (!(argsName.get("t").matches("mask")
                || argsName.get("t").matches("name") || argsName.get("t").matches("regex"))) {
            throw new IllegalArgumentException(String.format(
                    "Searching type must be \"name\", \"mask\" or \"regex\": %s", argsName.get("t")));
        }
        if (!argsName.get("o").matches(".*\\..*")) {
            throw new IllegalArgumentException(String.format(
                    "The output file must have an extension in the end of string: %s",
                    argsName.get("o")));
        }
        if (argsName.get("t").equals("mask") && (!argsName.get("n").startsWith("*"))) {
            throw new IllegalArgumentException(String.format(
                    "While searching by mask the string must start with \"*\": %s", argsName.get("n")));
        }
        if (argsName.get("t").equals("name") && (!argsName.get("n").matches(".*\\..*"))) {
            throw new IllegalArgumentException(String.format(
                    "While searching by name the string must contain \".\" for the format: %s", argsName.get("n")));
        }
        if (argsName.get("t").equals("regex") && (!argsName.get("n").startsWith(".*"))) {
            throw new IllegalArgumentException(String.format(
                    "While searching by regex the string must start with \".*\": %s", argsName.get("n")));
        }
    }

    private void resultToFile(List<Path> sources, ArgsName argsName) {
        try (PrintWriter printWriter = new PrintWriter(
                new FileWriter(argsName.get("o"))
        )) {
            sources.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Predicate<Path> searchType(ArgsName argsName) {
        Predicate<Path> condition = null;
        if (argsName.get("t").equals("name")) {
            condition = path -> path.toFile().getName().matches(argsName.get("n"));
        }
        if (argsName.get("t").equals("mask")) {
            String mask = argsName.get("n")
                            .replace(".", "\\.")
                            .replace("?", ".?")
                            .replace("*", ".*");
            condition = path -> path.toFile().getName().matches(mask);
        }
        if (argsName.get("t").equals("regex")) {
            Pattern pattern = Pattern.compile(argsName.get("n"));
            condition = path -> pattern.matcher(path.toFile().getName()).matches();
        }
        return condition;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("This is file search tool. You have to enter 4 program arguments: "
                + "d - directory for searching, n - file name or regular expression,"
                + System.lineSeparator() + "t - search type"
                + "(name, mask or regex), o - output file");
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of parameters to realize the program.");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        FileSearchTool fileSearchTool = new FileSearchTool();
        List<Path> list = Search.search(Path.of(argsName.get("d")), fileSearchTool.searchType(argsName));
        fileSearchTool.resultToFile(list, argsName);
    }
}
