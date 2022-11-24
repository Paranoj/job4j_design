package ru.job4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.List;

public class CSVReader {

    public static void validate(ArgsName argsName) {
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format(
                    "Wrong format added to args. Add .csv: %s", argsName.get("path")));
        }
        if (!Files.exists(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException(String.format(
                    "File doesn't exist in the chosen directory: %s", argsName.get("path")));
        }
        if (!argsName.get("out").endsWith("stdout") || !Files.exists(Path.of(argsName.get("out")))) {
            throw new IllegalArgumentException(String.format(
                    "Out must contain stdout or path to file: %s", argsName.get("out")));
        }
        if (!argsName.get("delimiter").endsWith(";") && !argsName.get("delimiter").endsWith(",")) {
            throw new IllegalArgumentException(String.format(
                    "Delimiter must contain ; or ,: %s", argsName.get("delimiter")));
        }
    }

    public static void handle(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        String[] filter = argsName.get("filter").split(",");
        int[] index = new int[filter.length];
        List<String> list = new ArrayList<>();
        try (var scanner = new Scanner(file).useDelimiter(argsName.get("delimiter"))) {
            while (scanner.hasNext()) {
                StringJoiner stringJoiner = new StringJoiner(argsName.get("delimiter"));
                String[] line = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < filter.length; i++) {
                    for (int j = 0; j < line.length; j++) {
                        if (filter[i].equals(line[j])) {
                            index[i] = j;
                        }
                    }
                }
                for (int j : index) {
                    stringJoiner.add(line[j]);
                }
                list.add(stringJoiner.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(argsName.get("out")))) {
            for (String s : list) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of parameters to realize the program.");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}
